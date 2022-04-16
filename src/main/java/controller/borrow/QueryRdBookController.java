package controller.borrow;

import Utils.ResponseDataMap;
import controller.controllerEnum.BorrowEnum;
import pojo.Book;
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
 * 日期:2022年04月16日10时36分
 */
@WebServlet(name = "QueryRdBookController", urlPatterns = {"/QueryRdBookController"})
public class QueryRdBookController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("content-type", "text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();

        int rdId = Integer.parseInt(req.getParameter("rdId"));

        List<Book> books = queryBookList(rdId);
        List<Borrow> borrows = queryBorrowByBooks(rdId,books);
        BorrowEnum borrowEnum;

        /**
         * 没有未还的书的记录 QUERY_NONE_BOOK_ERROR
         * 有未还的书的记录 返回书的json QUERY_SUCCESS
         */
        if (books.size() == 0) {
            borrowEnum = BorrowEnum.QUERY_NONE_BOOK_ERROR;
        } else {
            borrowEnum = BorrowEnum.QUERY_SUCCESS;
        }

        /**
         * 回复数据封装
         */
        ResponseDataMap sendData = new ResponseDataMap();
        sendData.setStatus(borrowEnum.ordinal());
        sendData.setMsg(borrowEnum.toString());
        sendData.setData(books);
        sendData.put("borrows",borrows);

        out.write(sendData.toJson());
    }


    public List<Book> queryBookList(int rdId) {
        BorrowService borrowService = new BorrowServiceImpl();
        return borrowService.noReturnBook(rdId);
    }

    public List<Borrow> queryBorrowByBooks(int rdId, List<Book> books) {
        BorrowService borrowService = new BorrowServiceImpl();
        return borrowService.queryBorrowByBooks(rdId, books);
    }

}
