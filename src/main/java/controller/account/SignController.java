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

        /**
         * 用户名已被注册           SIGN_IDENTIFICATION_EXIST_ERROR
         * 读者ID存在(已经被注册过)  SIGN_RDID_EXIST_ERROR
         * 学号与姓名不匹配         SIGN_RDID_MATCH_RDNAME_ERROR
         * 注册成功               SIGN_SUCCESS
         * 未知错误               UNKNOWN_ERROR
         */
        if (accountService.identificationIsExist(account.getIdentification())) {
            return AccountEnum.SIGN_IDENTIFICATION_EXIST_ERROR;
        }

        if (accountService.accountIsExist(account.getRdId())) {
            return AccountEnum.SIGN_RDID_EXIST_ERROR;
        }

        if (!(readerService.queryReaderExist(account.getRdId(), name))) {
            return AccountEnum.SIGN_RDID_MATCH_RDNAME_ERROR;
        }

        if (accountService.addAccount(account) == 0) {
            return AccountEnum.SIGN_SUCCESS;
        }

        return AccountEnum.UNKNOWN_ERROR;
    }

}
