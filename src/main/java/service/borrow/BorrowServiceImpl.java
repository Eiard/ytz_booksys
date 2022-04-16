package service.borrow;

import dao.borrow.BorrowMapper;
import dao.borrow.BorrowMapperImpl;
import pojo.Book;
import pojo.Borrow;
import service.book.BookService;
import service.book.BookServiceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
    public List<Boolean> lendBorrows(List<Borrow> borrows, int readerTypeDayNum) {
        List<Boolean> booleans = new ArrayList<>();

        SimpleDateFormat SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String DataBorrow = SimpleDateFormat.format(Calendar.getInstance().getTime());
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_YEAR);
        calendar.set(Calendar.DAY_OF_YEAR, day + readerTypeDayNum);
        String DataBorrowPlan = SimpleDateFormat.format(new Date(calendar.getTimeInMillis()));

        for (Borrow borrow : borrows) {

            List<Book> books = noReturnBook(borrow.getRdId());
            int booksSize = books.size();

            int tick = 0;
            for (Book book : books) {
                if (borrow.getBkId() == book.getBkId()) {
                    /**
                     * 未还的书
                     * 又继续借阅 (默认FALSE)
                     * 直接break 说明这个borrow记录的书 该读者已经借阅过
                     * 去执行下一条借阅记录
                     */
                    break;
                }
                tick++;
            }
            if (tick == booksSize) {
                borrow.setDateBorrow(DataBorrow);
                borrow.setDateLendPlan(DataBorrowPlan);
                booleans.add(lendBorrow(borrow));
            } else {
                booleans.add(false);
            }
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

        /**
         * 获取现在还书的时间
         */
        SimpleDateFormat SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dataLendAct = SimpleDateFormat.format(Calendar.getInstance().getTime());

        System.out.println(dataLendAct);

        for (Borrow borrow : Borrows) {
            borrow.setDateLendAct(dataLendAct);
            booleans.add(returnBorrow(borrow));
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

    @Override
    public List<Borrow> queryBorrowByBooks(int rdId, List<Book> books) {
        List<Borrow> borrows = new ArrayList<>();

        List<Borrow> borrowList = queryAllBorrow();
        for (Book book : books) {
            for (Borrow borrow : borrowList) {
                if (rdId == borrow.getRdId() && book.getBkId() == borrow.getBkId()
                        && ("".equals(borrow.getDateLendAct()) || borrow.getDateLendAct() == null)) {
                    if (borrow.getBkId() == book.getBkId()) {
                        borrows.add(borrow);
                    }
                }
            }
        }
        return borrows;
    }
}