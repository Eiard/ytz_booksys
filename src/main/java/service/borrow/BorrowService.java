package service.borrow;

import pojo.Book;
import pojo.Borrow;

import java.util.List;

public interface BorrowService {
    /**
     * Using :
     *     BorrowService borrowService = new BorrowServiceImpl();
     *     borrowService.(Method)
     */

    /**
     * Add Borrow Information
     * User Interface
     * 借书 并且添加借书记录 (单本)
     *
     * @param borrow
     * @return boolean
     */
    boolean lendBorrow(Borrow borrow);

    /**
     * Add Borrows Information
     * User Interface
     * 借书 并且添加借书记录 (多本)
     *
     * @param borrows
     * @param readerTypeDayNum
     * @return List<Boolean>
     */
    List<Boolean> lendBorrows(List<Borrow> borrows, int readerTypeDayNum);

    /**
     * Return Borrow
     * User Interface
     * 还一本书
     *
     * @param borrow
     * @return boolean
     */
    boolean returnBorrow(Borrow borrow);

    /**
     * Return Borrow
     * User Interface
     * 还多本书
     *
     * @param Borrows
     * @return List<Boolean>
     */
    List<Boolean> returnBorrows(List<Borrow> Borrows);

    /**
     * Query All Borrow Information
     * Common interface
     *
     * @return List<Borrow>
     */
    List<Borrow> queryAllBorrow();

    /**
     * Query All No Return Book Information
     * User interface
     * 未还的书信息(查找某个读者)
     *
     * @param rdId
     * @return List<Book>
     */
    List<Book> noReturnBook(int rdId);

    /**
     * Query All Borrow Info By No Return Book
     * Common interface
     * 未还的借阅信息(查找某个读者)
     *
     * @param books
     * @return List<Borrow>
     */
    List<Borrow> queryBorrowByBooks(int rdId,List<Book> books);
}
