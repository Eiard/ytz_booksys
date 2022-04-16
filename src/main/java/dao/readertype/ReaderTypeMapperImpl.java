package dao.readertype;

import Utils.JdbcUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import pojo.ReaderType;

import java.util.List;

/**
 * # -*- coding:utf-8 -*- #
 * 作者:30671
 * 日期:2022年03月02日10时09分
 */
public class ReaderTypeMapperImpl implements ReaderTypeMapper {
    /**
     * jdbcTemplate object
     */
    private JdbcTemplate Sql;

    public ReaderTypeMapperImpl() {
        Sql = new JdbcTemplate(JdbcUtils.getDataSource());
    }

    @Override
    public int addReaderType(ReaderType readerType) {
        String sql = "INSERT INTO readertype " +
                "(rdTypeName,canLendQty,canLendDay) " +
                "VALUES (?,?,?)";

        return Sql.update(sql,
                readerType.getRdTypeName(),
                readerType.getCanLendQty(),
                readerType.getCanLendDay());
    }

    @Override
    public int deleteReaderType(int rdType) {
        String sql = "DELETE FROM readertype WHERE rdType = ?";

        return Sql.update(sql, rdType);
    }

    @Override
    public int updateReaderType(ReaderType readerType) {
        String sql = "UPDATE readertype SET " +
                "rdTypeName = ?,canLendQty = ?,canLendDay = ? " +
                "WHERE rdType = ?";

        return Sql.update(sql,
                readerType.getRdTypeName(),
                readerType.getCanLendQty(),
                readerType.getCanLendDay(),
                readerType.getRdType());
    }

    @Override
    public List<ReaderType> queryAllReaderType() {
        String sql = "SELECT * FROM readertype";

        return Sql.query(sql, new BeanPropertyRowMapper<ReaderType>(ReaderType.class));
    }
}
