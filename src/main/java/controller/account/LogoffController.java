package controller.account;

import Utils.ResponseDataMap;
import controller.controllerEnum.AccountEnum;
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
import java.io.PrintWriter;

/**
 * # -*- coding:utf-8 -*- #
 * 作者:30671
 * 日期:2022年04月13日23时28分
 */
@WebServlet(name = "LogoffController", urlPatterns = {"/LogoffController"})
public class LogoffController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("content-type", "text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();

        String identification = req.getParameter("identification");

        AccountEnum accountEnum = logOff(identification);

        ResponseDataMap sendData = new ResponseDataMap();
        sendData.setStatus(accountEnum.ordinal());
        sendData.setMsg(accountEnum.toString());

        out.write(sendData.toJson());
    }

    /**
     * Log Off Account
     * 注销时应判断
     *
     * @param identification
     * @return AccountEnum
     */
    protected AccountEnum logOff(String identification) {
        AccountService accountService = new AccountServiceImpl();
        BorrowService borrowService = new BorrowServiceImpl();

        Account account = accountService.queryOneAccount(identification);

        /**
         * 没有未还的书籍(可注销)     LOGOFF_SUCCESS
         * 有未还的书籍(不可注销)     LOGOFF_ERROR
         */
        if (borrowService.noReturnBook(account.getRdId()).size() == 0) {
            accountService.deleteAccount(identification);
            return AccountEnum.LOGOFF_SUCCESS;
        }
        return AccountEnum.LOGOFF_ERROR;
    }
}
