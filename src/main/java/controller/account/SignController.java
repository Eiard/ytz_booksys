package controller.account;

import controller.controllerEnum.AccountEnum;
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
     *
     * @param account
     * @param name
     * @return AccountEnum
     */
    protected AccountEnum sign(Account account, String name) {
        AccountService accountService = new AccountServiceImpl();
        ReaderService readerService = new ReaderServiceImpl();

        if (accountService.identificationIsExist(account.getIdentification())) {
            return AccountEnum.SIGN_IDENTIFICATION_EXIST_ERROR;
        }

        if (accountService.accountIsExist(account.getRdId())) {
            return AccountEnum.SIGN_RDID_EXIST_ERROR;
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
