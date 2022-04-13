package controller.account;

import pojo.Account;
import service.account.AccountService;
import service.account.AccountServiceImpl;
import service.borrow.BorrowService;
import service.borrow.BorrowServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * # -*- coding:utf-8 -*- #
 * 作者:30671
 * 日期:2022年04月13日23时28分
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LogoffController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



    }

    /**
     * Log Off Account
     * 注销时应判断该账号 书籍是否全部归还
     * return
     * 0    注销成功
     * 1    有未还的书籍 无法注销
     *
     * @param identification
     * @return int
     */
    protected int logOff(String identification) {
        AccountService accountService = new AccountServiceImpl();
        BorrowService borrowService = new BorrowServiceImpl();

        // 先找到自己所有信息
        Account account = accountService.queryOneAccount(identification);

        if (borrowService.noReturnBook(account.getRdId()).size() == 0) {
            accountService.deleteAccount(identification);
            return 0;
        }
        return 1;
    }
}
