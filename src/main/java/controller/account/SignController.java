package controller.account;

import Utils.ResponseDataMap;
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
import java.io.PrintWriter;

/**
 * # -*- coding:utf-8 -*- #
 * 作者:30671
 * 日期:2022年04月13日23时16分
 */

@WebServlet(name = "SignController", urlPatterns = {"/SignController"})
public class SignController extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("content-type", "text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();

        /**
         * 获取
         *      用户名
         *      密码
         */
        String identification = req.getParameter("identification");
        String password = req.getParameter("password");
        String QQ = req.getParameter("QQ");
        int rdId = Integer.parseInt(req.getParameter("rdId"));
        String rdName = req.getParameter("rdName");

        Account account = new Account();
        account.setIdentification(identification);
        account.setPassword(password);
        account.setQQ(QQ);
        account.setRdId(rdId);

        /**
         * 执行操作
         * 返回状态码
         */
        AccountEnum accountEnum  = sign(account,rdName);

        /**
         * 回复数据封装
         */
        ResponseDataMap sendData = new ResponseDataMap();
        sendData.setStatus(accountEnum.ordinal());
        sendData.setMsg(accountEnum.toString());

        out.write(sendData.toJson());
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

        if (accountService.addAccount(account) == 1) {
            return AccountEnum.SIGN_SUCCESS;
        }

        return AccountEnum.UNKNOWN_ERROR;
    }

}
