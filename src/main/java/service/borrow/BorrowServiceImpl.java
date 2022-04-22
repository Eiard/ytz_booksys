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

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String DataBorrow = SimpleDateFormat.format(calendar.getTime());
        int day = calendar.get(Calendar.DAY_OF_YEAR);
        calendar.set(Calendar.DAY_OF_YEAR, day + readerTypeDayNum);
        String DataBorrowPlan = SimpleDateFormat.format(new Date(calendar.getTimeInMillis()));

        for (Borrow borrow : borrows) {

            /**
             * 所有未还书籍信息
             */
            List<Book> books = noReturnBook(borrow.getRdId());
            int booksSize = books.size();

            int tick = 0;
            for (Book book : books) {
                if (borrow.getBkId() == book.getBkId()) {
                    /**
                     * 避免未还的书再借
                     * 直接break 说明这个borrow记录的书 该读者已经借阅过
                     * 去执行下一条借阅记录
                     */
                    break;
                }
                tick++;
            }
            /**
             * 说明现在借的书与之前没还的书没冲突
             * 可以借
             */
            if (tick == booksSize) {
                borrow.setDateBorrow(DataBorrow);
                borrow.setDateLendPlan(DataBorrowPlan);
                booleans.add(lendBorrow(borrow));
            } else {
                /**
                 * 现在借的书 之前借了并且还没还
                 */
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
            if (rdId == borrow.getRdId() && borrow.getIsReturn() == 0) {

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
    public List<Borrow> queryBorrowByrdId(int rdId) {
        List<Borrow> borrows = new ArrayList<>();

        List<Borrow> borrowList = queryAllBorrow();
        for (Borrow borrow : borrowList) {
            if (rdId == borrow.getRdId() && borrow.getIsReturn() == 0) {
                borrows.add(borrow);
            }
        }

        return borrows;
    }

    @Override
    public int queryOverdueNum(int rdId) {
        List<Borrow> borrows = queryAllBorrow();

        int overDueNum = 0;

        for (Borrow borrow : borrows) {
            if (borrow.getRdId() == rdId && borrow.getOverdue() == 1) {
                overDueNum++;
            }
        }

        return overDueNum;
    }


    public List<Borrow> buildBorrowList( Integer rdId,List<Integer> bkIds) {
        List<Borrow> borrows = null;
        for (int i = 0; i < bkIds.size(); i++) {
            Borrow borrow = new Borrow();
            borrow.setRdId(rdId);
            borrow.setBkId(bkIds.get(i));
            borrows.add(borrow);
        }
        return borrows;
    }
}