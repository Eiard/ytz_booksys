package dao.borrow;

import Utils.JdbcUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import pojo.Borrow;

import java.util.List;

/**
 * # -*- coding:utf-8 -*- #
 * 作者:30671
 * 日期:2022年03月02日10时05分
 */
public class BorrowMapperImpl implements BorrowMapper {
    /**
     * jdbcTemplate object
     */
    private JdbcTemplate Sql;

    public BorrowMapperImpl() {
        Sql = new JdbcTemplate(JdbcUtils.getDataSource());
    }

    @Override
    public int addBorrow(Borrow borrow) {
        String sql =
                "INSERT INTO borrow " +
                        "(rdId,bkId,dateBorrow,dateLendPlan) " +
                        "VALUES (?,?,?,?)";

        return Sql.update(sql,
                borrow.getRdId(),
                borrow.getBkId(),
                borrow.getDateBorrow(),
                borrow.getDateLendPlan());
    }

    @Override
    public int updateBorrow(Borrow borrow) {
        String sql =
                "UPDATE borrow SET " +
                        "dateLendAct = ?" +
                        "WHERE rdId = ? AND bkId = ? AND dateBorrow = ?";

        return Sql.update(sql,
                borrow.getDateLendAct(),
                borrow.getRdId(),
                borrow.getBkId(),
                borrow.getDateBorrow());
    }

    @Override
    public List<Borrow> queryAllBorrow() {
        String sql = "SELECT * FROM borrow";

        return Sql.query(sql, new BeanPropertyRowMapper<Borrow>(Borrow.class));
    }
}
