package dao.book;

import Utils.JdbcUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import pojo.Book;

import java.util.List;

/**
 * # -*- coding:utf-8 -*- #
 * 作者:30671
 * 日期:2022年03月02日08时35分
 */
public class BookMapperImpl implements BookMapper {
    /**
     * jdbcTemplate object
     */
    private JdbcTemplate Sql;

    public BookMapperImpl() {
        Sql = new JdbcTemplate(JdbcUtils.getDataSource());
    }

    @Override
    public int addBook(Book book) {
        String sql =
                "INSERT INTO book " +
                        "(bkName,bkAuthor,bkPress," +
                        "bkPrice,bkNum,bkState,bkImageUrl) " +
                        "VALUES (?,?,?,?,?,?,?)";

        return Sql.update(sql,
                book.getBkName(),
                book.getBkAuthor(),
                book.getBkPress(),
                book.getBkPrice(),
                book.getBkNum(),
                book.getBkState(),
                book.getBkImageUrl());
    }

    @Override
    public int updateBook(Book book) {
        String sql =
                "UPDATE book SET " +
                        "bkName = ?,bkAuthor = ?," +
                        "bkPress = ?,bkPrice = ?," +
                        "bkNum = ?,bkState = ? ," +
                        "bkImageUrl = ? WHERE bkId = ?";

        return Sql.update(sql,
                book.getBkName(),
                book.getBkAuthor(),
                book.getBkPress(),
                book.getBkPrice(),
                book.getBkNum(),
                book.getBkState(),
                book.getBkImageUrl(),
                book.getBkId());
    }

    @Override
    public List<Book> queryAllBook() {
        String sql = "SELECT * FROM book";

        return Sql.query(sql, new BeanPropertyRowMapper<Book>(Book.class));
    }
}
