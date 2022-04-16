package controller.borrow;

import Utils.GsonUtils;
import Utils.ResponseDataMap;
import controller.controllerEnum.BorrowEnum;
import pojo.Borrow;
import service.borrow.BorrowService;
import service.borrow.BorrowServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * # -*- coding:utf-8 -*- #
 * 作者:30671
 * 日期:2022年04月16日10时12分
 */
@WebServlet(name = "ReturnBookController", urlPatterns = {"/ReturnBookController"})
public class ReturnBookController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("content-type", "text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();

        List<Borrow> borrows = null;
        BorrowEnum borrowEnum = BorrowEnum.RETURN_SUCCESS;
        ResponseDataMap sendData = new ResponseDataMap();
        try {
            borrows = GsonUtils.strToJavaBeanList(req.getParameter("borrows"));
        } catch (Exception e) {
            borrowEnum = BorrowEnum.RETURN_FORMAT_ERROR;
            sendData.setStatus(borrowEnum.ordinal());
            sendData.setMsg(borrowEnum.toString());
            out.write(sendData.toJson());
            return;
        }


        List<Boolean> booleans = ReturnBooks(borrows);

        /**
         * 回复数据封装
         */
        sendData.setStatus(borrowEnum.ordinal());
        sendData.setMsg(borrowEnum.toString());
        sendData.setData(booleans);

        out.write(sendData.toJson());
    }

    public List<Boolean> ReturnBooks(List<Borrow> borrows) {
        BorrowService borrowService = new BorrowServiceImpl();
        return borrowService.returnBorrows(borrows);
    }


}
