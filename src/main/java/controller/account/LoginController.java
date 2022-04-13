package controller.account;

import controller.controllerEnum.AccountEnum;
import service.account.AccountService;
import service.account.AccountServiceImpl;

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
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
