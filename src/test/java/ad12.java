import Utils.FastJsonUtils;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * # -*- coding:utf-8 -*- #
 * 作者:30671
 * 日期:2022年04月14日12时59分
 */
public class ad12 {

    @Test
    public void asd12() {
        String s = new String("谭虎");
        System.out.println(FastJsonUtils.objectToJsonString(s));


        List<Borrow> borrows = new ArrayList<>();

        Borrow borrow1 = new Borrow();
        borrow1.setRdId(2);
        borrow1.setBkId(1);
        Borrow borrow2 = new Borrow();
        borrow2.setRdId(3);
        borrow2.setBkId(1);

        borrows.add(borrow1);
        borrows.add(borrow2);

        System.out.println(FastJsonUtils.listToJsonString(borrows));


    }

}
