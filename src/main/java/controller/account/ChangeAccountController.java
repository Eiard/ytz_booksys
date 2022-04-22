package controller.account;

import Utils.FastJsonUtils;
import Utils.ResponseDataMap;
import com.alibaba.fastjson.TypeReference;
import controller.controllerEnum.AccountEnum;
import pojo.Account;

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

        ResponseDataMap sendData = new ResponseDataMap();
        AccountEnum accountEnum;

        try {
            Account account = FastJsonUtils.strToJavaBean(req.getParameter("Account"), new TypeReference<Account>() {
            });
        }catch (Exception e){
            accountEnum= AccountEnum.CHANGE_ACCOUNT_JSON_TYPE_ERROR;
            sendData.setStatus(accountEnum.ordinal());
            sendData.setMsg(accountEnum.toString());
            out.write(sendData.toJson());
            return;
        }
        String newPassword = req.getParameter("newPassword");






    }

    protected AccountEnum changeAccountInfo(Account account, String newPassword) {


        return AccountEnum.UNKNOWN_ERROR;
    }


}
