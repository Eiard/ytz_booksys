import Utils.GsonUtils;
import controller.controllerEnum.AccountEnum;
import dao.book.BookMapper;
import dao.book.BookMapperImpl;
import org.junit.Test;
import pojo.Account;
import pojo.Book;
import pojo.Borrow;
import service.book.BookService;
import service.book.BookServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * # -*- coding:utf-8 -*- #
 * 作者:30671
 * 日期:2022年04月14日12时59分
 */
public class ad12 {

    @Test
    public void asd12() {

        // Borrow borrow = new Borrow();
        //
        // borrow.setBkId(1);
        // borrow.setRdId(3);
        // borrow.setDateBorrow();
        // borrow.setDateLendAct();
        int readerTypeNum = 10;
        SimpleDateFormat SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String DataBorrow = SimpleDateFormat.format(Calendar.getInstance().getTime());
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_YEAR);
        calendar.set(Calendar.DAY_OF_YEAR, day + readerTypeNum);
        String DataBorrowPlan = SimpleDateFormat.format(new Date(calendar.getTimeInMillis()));
    }

}
