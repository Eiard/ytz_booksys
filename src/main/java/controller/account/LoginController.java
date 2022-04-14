package controller.account;

import Utils.GsonUtils;
import Utils.ResponseDataMap;
import controller.controllerEnum.AccountEnum;
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
 * 日期:2022年03月03日10时52分
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");

        AccountEnum accountEnum = login(userName,password);

        ResponseDataMap sendData = new ResponseDataMap();

        if (accountEnum == AccountEnum.LOGIN_USER_SUCCESS){
            sendData.setStatus(accountEnum.ordinal());
        }


        PrintWriter writer = resp.getWriter();

        writer.write(sendData.toJson(GsonUtils.GsonSerializerFeature.WriteNullValue));


    }

    /**
     * Check Login Information
     * 登录判断
     *
     * @param identification
     * @param password
     * @return AccountEnum
     */
    protected AccountEnum login(String identification, String password) {
        AccountService accountService = new AccountServiceImpl();

        int status = accountService.identificationAndPassword(identification, password);

        /**
         *   账号或密码错误   LOGIN_ACCOUNT_PASSWORD_ERROR
         *   USER登录成功    LOGIN_USER_SUCCESS
         *   ROOT登录成功    LOGIN_ROOT_SUCCESS
         *   未知错误        UNKNOWN_ERROR
         */
        if (status == -1) {
            return AccountEnum.LOGIN_ACCOUNT_PASSWORD_ERROR;
        } else if (status == 0) {
            return AccountEnum.LOGIN_USER_SUCCESS;
        } else if (status == 1) {
            return AccountEnum.LOGIN_ROOT_SUCCESS;
        }
        return AccountEnum.UNKNOWN_ERROR;
    }


}
