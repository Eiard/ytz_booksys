package controller.account;

import pojo.Account;
import service.account.AccountService;
import service.account.AccountServiceImpl;
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
 * 日期:2022年04月13日23时16分
 */

@WebServlet(name = "SignController", urlPatterns = {"/SignController"})
public class SignController extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }

    /**
     * Check Register Information
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
    protected int sign(Account account, String name) {
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
        if (!(readerService.queryReaderExist(account.getRdId(), name))) {
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

}
