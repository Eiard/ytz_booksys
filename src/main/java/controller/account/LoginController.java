package controller.account;

import Utils.ResponseDataMap;
import controller.controllerEnum.AccountEnum;
import pojo.Account;
import pojo.Reader;
import pojo.ReaderType;
import service.account.AccountService;
import service.account.AccountServiceImpl;
import service.borrow.BorrowService;
import service.borrow.BorrowServiceImpl;
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
        String identification = req.getParameter("identification");
        String password = req.getParameter("password");

        /**
         * 执行操作
         *   账号或密码错误   LOGIN_ACCOUNT_PASSWORD_ERROR
         *   USER登录成功    LOGIN_USER_SUCCESS
         *   ROOT登录成功    LOGIN_ROOT_SUCCESS
         *   未知错误        UNKNOWN_ERROR
         */
        AccountService accountService = new AccountServiceImpl();

        AccountEnum accountEnum = accountService.login(identification, password);

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
     * 回显数据方法封装
     *
     * @param sendData
     * @param accountEnum
     * @return ResponseDataMap
     */
    public ResponseDataMap dataEncapsulation(ResponseDataMap sendData, String identification, AccountEnum accountEnum) {
        AccountService accountService = new AccountServiceImpl();

        Account account = accountService.queryOneAccount(identification);
        /**
         * 密码擦除
         */
        account.setPassword("");
        sendData.setData(account);

        if (accountEnum == AccountEnum.LOGIN_ROOT_SUCCESS) {
            return sendData;
        } else if (accountEnum == AccountEnum.LOGIN_USER_SUCCESS) {
            ReaderService readerService = new ReaderServiceImpl();
            ReaderTypeService readerTypeService = new ReaderTypeServiceImpl();
            BorrowService borrowService = new BorrowServiceImpl();

            Reader reader = readerService.queryOneReader(account.getRdId());
            sendData.put("reader", reader);

            ReaderType readerType = readerTypeService.queryOneReaderType(reader.getRdType());
            sendData.put("readerType", readerType);

            int overDueNum = borrowService.queryOverdueNum(account.getRdId());
            sendData.put("overDueNum", overDueNum);

        }

        return sendData;
    }


}
