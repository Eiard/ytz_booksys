package dao.book;

import pojo.Book;

import java.util.List;

public interface BookMapper {
    /**
     * Using :
     *     BookMapper bookMapper = new BookMapperImpl();
     *     bookMapper.(Method)
     */

    /**
     * Add Book
     * Root interface
     *
     * 添加一本书
     *
     * @param book
     * @return int
     */
    int addBook(Book book);

    /**
     * Update Book
     * Root interface
     *
     * 更新一本书信息  管理员
     *
     * @param book
     * @return int
     */
    int updateBook(Book book);

    /**
     * Query All Book
     * Common interface
     *
     * 查询所有书信息
     *
     * @return List<Book>
     */
    List<Book> queryAllBook();
}
