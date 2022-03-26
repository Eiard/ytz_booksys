package controller.account;

import pojo.Account;
import service.account.AccountService;
import service.account.AccountServiceImpl;
import service.borrow.BorrowService;
import service.borrow.BorrowServiceImpl;
import service.reader.ReaderService;
import service.reader.ReaderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * # -*- coding:utf-8 -*- #
 * 作者:30671
 * 日期:2022年03月03日10时52分
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/Account.do"})
public class AccountController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    /**
     * Check Login Information
     * <p>
     * 登录判断
     * return
     * -1   账号或密码错误
     * 0    User
     * 1    Root
     *
     * @param identification
     * @param password
     * @return int
     */
    public int login(String identification, String password) {
        AccountService accountService = new AccountServiceImpl();
        return accountService.identificationAndPassword(identification, password);
    }

    /**
     * Check Sign Up Information
     * <p>
     * 注册判断
     * return
     * 0    注册成功
     * 1    用户名被注册过
     * 2    学号被注册过
     * 3    学号和姓名不匹配 (冒名注册)
     * 4    服务器问题注册失败
     *
     * @param account
     * @param name
     * @return int
     */
    public int signUp(Account account, String name) {
        AccountService accountService = new AccountServiceImpl();
        ReaderService readerService = new ReaderServiceImpl();

        /**
         * 第一步 判断用户名是否被注册过
         */
        if (accountService.identificationIsExist(account.getIdentification())) {
            return 1;
        }

        /**
         * 第二步 判断学号是否被注册
         */
        if (accountService.accountIsExist(account.getRdId())) {
            return 2;
        }

        /**
         * 第三步 判断姓名 与 学号是否匹配是否存在
         */
        if (readerService.queryReaderExist(account.getRdId(), name)) {
            return 3;
        }


        /**
         * 第四步 注册
         */
        if (accountService.addAccount(account) == 0) {
            return 4;
        }

        return 0;
    }

    /**
     * Log Out Account
     * <p>
     * 注销
     * <p>
     * 注销时应判断该账号 书籍是否全部归还
     * return
     * 0    注销成功
     * 1    有未还的书籍 无法注销
     *
     * @param identification
     * @return int
     */
    public int logOut(String identification) {
        AccountService accountService = new AccountServiceImpl();
        BorrowService borrowService = new BorrowServiceImpl();

        // 先找到自己所有信息
        Account account = accountService.queryOneAccount(identification);

        // 判断自己是否有未还的书 (此时不能注销)
        if (borrowService.noReturnBook(account.getRdId()).size() == 0) {
            accountService.deleteAccount(identification);
            return 0;
        }

        return 1;
    }

}
