package dao.reader;

import Utils.JdbcUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import pojo.Reader;

import java.util.List;

/**
 * # -*- coding:utf-8 -*- #
 * 作者:30671
 * 日期:2022年03月02日10时07分
 */
public class ReaderMapperImpl implements ReaderMapper {
    /**
     * jdbcTemplate object
     */
    private JdbcTemplate Sql;

    public ReaderMapperImpl() {
        Sql = new JdbcTemplate(JdbcUtils.getDataSource());
    }

    @Override
    public int addReader(Reader reader) {
        String sql = "INSERT INTO reader " +
                "(rdType,rdName,rdDept,rdState) " +
                "VALUES (?,?,?,?)";

        return Sql.update(sql,
                reader.getRdType(),
                reader.getRdName(),
                reader.getRdDept(),
                reader.getRdState());

    }

    @Override
    public int updateReader(Reader reader) {
        String sql = "UPDATE reader SET " +
                "rdType = ?,rdName = ?," +
                "rdDept = ?,rdState = ? " +
                "WHERE rdId = ?";

        return Sql.update(sql,
                reader.getRdType(),
                reader.getRdName(),
                reader.getRdDept(),
                reader.getRdState());

    }

    @Override
    public List<Reader> queryAllReader() {
        String sql = "SELECT * FROM reader";

        return Sql.query(sql, new BeanPropertyRowMapper<Reader>(Reader.class));
    }
}
