package controller.account;

import Utils.ResponseDataMap;
import pojo.Account;
import service.account.AccountService;
import service.account.AccountServiceImpl;
import service.borrow.BorrowService;
import service.borrow.BorrowServiceImpl;
import service.reader.ReaderService;
import service.reader.ReaderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * # -*- coding:utf-8 -*- #
 * 作者:30671
 * 日期:2022年03月03日10时52分
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {


        String name = req.getParameter("name");
        String password = req.getParameter("password");

        PrintWriter out = resp.getWriter();



        ResponseDataMap responseDataMap = new ResponseDataMap(1, "2", 3);
        out.println(responseDataMap.toJson());


        Map map = new HashMap();
        map.put("name", "zahngsan");
        map.put("age", 19);


        System.out.println(name);
        System.out.println(password);
        System.out.println("dopost");


    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(1);
    }

    /**
     * Check Login Information
     * 登录判断
     * return
     * -1   账号或密码错误
     * 0    User=
     * 1    Root
     *
     * @param identification
     * @param password
     * @return int
     */
    protected int login(String identification, String password) {
        AccountService accountService = new AccountServiceImpl();
        return accountService.identificationAndPassword(identification, password);
    }



}
