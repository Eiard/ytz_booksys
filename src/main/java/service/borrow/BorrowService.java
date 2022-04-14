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
     * @return List<Boolean>
     */
    List<Boolean> lendBorrows(List<Borrow> borrows);

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
     * 未还的书信息(查找读者自己的)
     *
     * @param rdId
     * @return List<Book>
     */
    List<Book> noReturnBook(int rdId);
}
