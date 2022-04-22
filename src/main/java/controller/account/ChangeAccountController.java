package controller.account;

import Utils.FastJsonUtils;
import Utils.ResponseDataMap;
import com.alibaba.fastjson.TypeReference;
import controller.controllerEnum.AccountEnum;
import pojo.Account;
import service.account.AccountService;
import service.account.AccountServiceImpl;

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
 * 日期:2022年04月17日17时18分
 */
@WebServlet(name = "ChangeAccountController", urlPatterns = {"/ChangeAccountController"})
public class ChangeAccountController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("content-type", "text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();

        Account account;
        String newPassword;

        String status = req.getParameter("status");
        ResponseDataMap sendData = new ResponseDataMap();
        AccountEnum accountEnum;
        if ("0".equals(status)) {
            /**
             * 更改的QQ
             */
            try {
                account = FastJsonUtils.strToJavaBean(req.getParameter("account"), new TypeReference<>() {
                });
            } catch (Exception e) {
                accountEnum = AccountEnum.CHANGE_ACCOUNT_JSON_TYPE_ERROR;
                sendData.setStatus(accountEnum.ordinal());
                sendData.setMsg(accountEnum.toString());
                out.write(sendData.toJson());
                return;
            }
            accountEnum = changeAccountInfo(account, account.getPassword());

        } else if ("1".equals(status)) {
            /**
             * 更改密码
             */
            try {
                account = FastJsonUtils.strToJavaBean(req.getParameter("account"), new TypeReference<>() {
                });
                newPassword = req.getParameter("newPassword");
            } catch (Exception e) {
                accountEnum = AccountEnum.CHANGE_ACCOUNT_JSON_TYPE_ERROR;
                sendData.setStatus(accountEnum.ordinal());
                sendData.setMsg(accountEnum.toString());
                out.write(sendData.toJson());
                return;
            }

            accountEnum = changeAccountInfo(account, newPassword);

        } else {
            accountEnum = AccountEnum.STATUS_ERROR;
        }

        sendData.setStatus(accountEnum.ordinal());
        sendData.setMsg(accountEnum.toString());
        out.write(sendData.toJson());
    }

    protected AccountEnum changeAccountInfo(Account account, String newPassword) {
        AccountService accountService = new AccountServiceImpl();
        account.setPassword(newPassword);
        if (accountService.updateAccount(newPassword, account.getQQ(), account.getIdentification()) == 1) {
            return AccountEnum.CHANGE_INFORMATION_SUCCESS;
        }

        return AccountEnum.UNKNOWN_ERROR;
    }

}
