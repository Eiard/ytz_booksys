import controller.controllerEnum.AccountEnum;
import dao.book.BookMapper;
import dao.book.BookMapperImpl;
import org.junit.Test;

/**
 * # -*- coding:utf-8 -*- #
 * 作者:30671
 * 日期:2022年04月14日12时59分
 */
public class ad12 {

    @Test
    public void asd12(){
        BookMapper bookMapper = new BookMapperImpl();

        System.out.println(bookMapper.queryAllBook());

   }

}
