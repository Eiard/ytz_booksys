import Utils.GsonUtils;
import org.junit.Test;
import service.borrow.BorrowService;
import service.borrow.BorrowServiceImpl;

/**
 * # -*- coding:utf-8 -*- #
 * 作者:30671
 * 日期:2022年03月26日13时32分
 */
public class a21 {
    @Test
    public void s2123(){
        BorrowService borrowService = new BorrowServiceImpl();
        System.out.println(borrowService.noReturnBook(1).size());
    }
}
