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
 * 日期:2022年03月03日10时52分
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/Account.do"})
public class AccountController extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(1);
    }

    /**
     * Check Login Information
     *
     * 登录判断
     *
     * @param identification
     * @param password
     * @return int
     */
    public int login(String identification, String password) {

        return 0;
    }

    /**
     * Check Sign Up Information
     *
     * 注册判断
     *
     * @param account
     * @param name
     * @return int
     */
    public int signUp(Account account, String name) {
        /**
         * 第一步 判断用户名是否被注册过
         */

        /**
         * 第二步 判断学号是否被注册
         */


        /**
         * 第三步 判断姓名 与 学号是否匹配是否存在
         */


        /**
         * 第四步 注册成功
         */

        return 4;
    }

    /**
     * Log Out Account
     *
     * 注销
     *
     * @param identification
     * @return boolean
     */
    public int logOut(String identification) {

        return 0;
    }

}
