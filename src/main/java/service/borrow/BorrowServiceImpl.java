package service.borrow;

import dao.borrow.BorrowMapper;
import dao.borrow.BorrowMapperImpl;
import pojo.Book;
import pojo.Borrow;
import service.book.BookService;
import service.book.BookServiceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * # -*- coding:utf-8 -*- #
 * 作者:30671
 * 日期:2022年03月03日13时12分
 */
public class BorrowServiceImpl implements BorrowService {
    /**
     * 相同类型 调用dao层对象
     */
    private BorrowMapper borrowMapper;

    /**
     * 不同类型 调用service层对象
     */
    private BookService bookService;

    public BorrowServiceImpl() {
        borrowMapper = new BorrowMapperImpl();
        bookService = new BookServiceImpl();
    }

    @Override
    public boolean lendBorrow(Borrow borrow) {
        return borrowMapper.addBorrow(borrow) == 1;
    }

    @Override
    public List<Boolean> lendBorrows(List<Borrow> borrows) {
        List<Boolean> booleans = new ArrayList<>();
        Collections.fill(booleans, false);
        int index = 0;

        /**
         * 这些书说明还没还
         */
        List<Book> books = noReturnBook(borrows.get(0).getRdId());


        for (Borrow borrow : borrows) {
            for (Book book : books){
                if(book.getBkId() == borrow.getRdId()){
                    /**
                     * 未还的书 又继续借阅 (默认FALSE)
                     */
                }else{
                    booleans.set(index, lendBorrow(borrow));
                }
            }
            index++;
        }
        return booleans;
    }

    @Override
    public boolean returnBorrow(Borrow borrow) {
        return borrowMapper.updateBorrow(borrow) == 1;
    }


    @Override
    public List<Boolean> returnBorrows(List<Borrow> Borrows) {
        List<Boolean> booleans = new ArrayList<>();
        Collections.fill(booleans, false);
        int index = 0;
        for (Borrow borrow : Borrows) {
            booleans.set(index, returnBorrow(borrow));
        }
        return booleans;
    }

    @Override
    public List<Borrow> queryAllBorrow() {
        return borrowMapper.queryAllBorrow();
    }

    @Override
    public List<Book> noReturnBook(int rdId) {
        List<Book> books = new ArrayList<>();

        List<Borrow> borrowList = queryAllBorrow();
        for (Borrow borrow : borrowList) {
            if (rdId == borrow.getRdId() && ("".equals(borrow.getDateLendAct()) || borrow.getDateLendAct() == null)) {
                List<Book> bookList = bookService.queryAllBook();
                for (Book book : bookList) {
                    if (borrow.getBkId() == book.getBkId()) {
                        books.add(book);
                    }
                }
            }
        }
        return books;
    }
}
