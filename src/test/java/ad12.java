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
import java.util.*;

/**
 * # -*- coding:utf-8 -*- #
 * 作者:30671
 * 日期:2022年04月14日12时59分
 */
public class ad12 {

    @Test
    public void asd12() {

        List<Borrow> borrows=  new ArrayList<>();

        Borrow borrow = new Borrow();
        borrow.setBkId(1);
        borrow.setRdId(2);
        borrow.setDateBorrow("2022-04-16 21:51:49");

        borrows.add(borrow);
        System.out.println(FastJsonUtils.listToJsonString(borrows));


    }

}
