package service.book;

import pojo.Book;

import java.util.List;

public interface BookService {
    /**
     * Using :
     *     BookService bookService = new BookServiceImpl();
     *     bookService.(Method)
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
     * Add Books
     * Root interface
     *
     * 添加多本书
     *
     * @param Books
     * @return int
     */
    int addBooks(List<Book> Books);

    /**
     * Update Book
     * Root interface
     *
     * 修改一本书
     *
     * @param book
     * @return int
     */
    int updateBook(Book book);

    /**
     * Update Books
     * Root interface
     *
     * 修改多本书
     *
     * @param Books
     * @return int
     */
    int updateBooks(List<Book> Books);

    /**
     * Query All Book
     * Common interface
     *
     * 查询所有书
     *
     * @return List<Book>
     */
    List<Book> queryAllBook();

    /**
     * Fuzzy Query By bkName And Author And BkPress
     *
     * 模糊查询 书名 作者 出版社
     *
     * @param bkName
     * @param bkAuthor
     * @param bkPress
     * @return List<Book>
     */
    List<Book> fuzzyQueryAllBook(String bkName, String bkAuthor,String bkPress);

}
