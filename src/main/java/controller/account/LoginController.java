package controller.account;

import Utils.ResponseDataMap;
import controller.controllerEnum.AccountEnum;
import pojo.Account;
import pojo.Reader;
import pojo.ReaderType;
import service.account.AccountService;
import service.account.AccountServiceImpl;
import service.reader.ReaderService;
import service.reader.ReaderServiceImpl;
import service.readertype.ReaderTypeService;
import service.readertype.ReaderTypeServiceImpl;

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
        resp.setHeader("content-type", "text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();

        /**
         * 获取
         *      用户名
         *      密码
         */
        String identification = new String(req.getParameter("identification").getBytes("ISO-8859-1"),"UTF-8");
        String password = req.getParameter("password");

        /**
         * 执行操作
         * 返回状态码
         */
        AccountEnum accountEnum = login(identification, password);

        /**
         * 回复数据封装
         */
        ResponseDataMap sendData = new ResponseDataMap();
        sendData.setStatus(accountEnum.ordinal());
        sendData.setMsg(accountEnum.toString());

        /**
         * 登录成功需要回发 Account信息
         */
        if (accountEnum == AccountEnum.LOGIN_USER_SUCCESS || accountEnum == AccountEnum.LOGIN_ROOT_SUCCESS) {
            sendData = dataEncapsulation(sendData, identification, accountEnum);
        }
        /**
         * 登录失败不需要回发 Account信息
         * data字段默认为空
         */

        out.write(sendData.toJson());
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


    /**
     * 回显数据方法封装
     *
     * @param sendData
     * @param accountEnum
     * @return ResponseDataMap
     */
    public ResponseDataMap dataEncapsulation(ResponseDataMap sendData, String identification, AccountEnum accountEnum) {
        AccountService accountService = new AccountServiceImpl();

        Account account = accountService.queryOneAccount(identification);
        account.setPassword("");
        sendData.setData(account);

        if (accountEnum == AccountEnum.LOGIN_ROOT_SUCCESS) {
            return sendData;
        } else if (accountEnum == AccountEnum.LOGIN_USER_SUCCESS) {
            ReaderService readerService = new ReaderServiceImpl();
            ReaderTypeService readerTypeService = new ReaderTypeServiceImpl();

            Reader reader = readerService.queryOneReader(account.getRdId());
            sendData.put("reader", reader);

            ReaderType readerType = readerTypeService.queryOneReaderType(reader.getRdType());
            sendData.put("readerType", readerType);
        }

        return sendData;
    }


}
