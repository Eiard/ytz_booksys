import Utils.FastJsonUtils;
import Utils.GsonUtils;
import Utils.JdbcUtils;
import controller.controllerEnum.AccountEnum;
import dao.book.BookMapper;
import dao.book.BookMapperImpl;
import dao.borrow.BorrowMapper;
import dao.borrow.BorrowMapperImpl;
import org.junit.Test;
import pojo.Account;
import pojo.Book;
import pojo.Borrow;
import service.book.BookService;
import service.book.BookServiceImpl;
import service.reader.ReaderService;
import service.reader.ReaderServiceImpl;

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
        Book book = new Book();
        book.setBkPrice(1);
        book.setBkName("132");
        book.setBkAuthor("12345");
        book.setBkPress("jsad");
        book.setBkNum(11);

        System.out.println(FastJsonUtils.objectToJsonString(book));

    }

}
