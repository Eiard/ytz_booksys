import Utils.GsonUtils;
import controller.controllerEnum.AccountEnum;
import dao.book.BookMapper;
import dao.book.BookMapperImpl;
import org.junit.Test;
import pojo.Book;

/**
 * # -*- coding:utf-8 -*- #
 * 作者:30671
 * 日期:2022年04月14日12时59分
 */
public class ad12 {

    @Test
    public void asd12(){
        Book book = new Book();
        book.setBkState((byte) 1);
        book.setBkAuthor("1");
        book.setBkNum(10);
        book.setBkImageUrl("!23");
        book.setBkName("123");
        book.setBkPrice(12.1);



        System.out.println(GsonUtils.objectToJsonString(book,GsonUtils.GsonSerializerFeature.WriteNullValue));

   }

}
