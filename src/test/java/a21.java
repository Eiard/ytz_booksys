import Utils.GsonUtils;
import Utils.ResponseDataMap;
import controller.account.AccountController;
import org.junit.Test;
import service.borrow.BorrowService;
import service.borrow.BorrowServiceImpl;

/**
 * # -*- coding:utf-8 -*- #
 * 作者:30671
 * 日期:2022年03月26日13时32分
 */
public class a21 {
    public enum LoginStatus {
        SUCCESS,

    }

    @Test
    public void s2123(){
        ResponseDataMap responseDataMap = new ResponseDataMap(a21.LoginStatus.SUCCESS.ordinal(), "2", 3);

        System.out.println(responseDataMap.toJson());
    }
}
