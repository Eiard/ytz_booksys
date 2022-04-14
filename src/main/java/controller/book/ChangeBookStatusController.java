package controller.book;

import Utils.GsonUtils;
import Utils.ResponseDataMap;
import controller.controllerEnum.AccountEnum;
import controller.controllerEnum.BookEnum;
import pojo.Book;
import service.book.BookService;
import service.book.BookServiceImpl;
import service.borrow.BorrowService;
import service.borrow.BorrowServiceImpl;

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
 * 日期:2022年04月14日21时31分
 */

@WebServlet(name = "ChangeBookStatusController", urlPatterns = {"/ChangeBookStatusController"})
public class ChangeBookStatusController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("content-type", "text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();

        Book book = GsonUtils.strToJavaBean(req.getParameter("book"),Book.class);

        System.out.println(book);

        BookEnum bookEnum = ChangeBookStatus(book);

        ResponseDataMap sendData = new ResponseDataMap();
        sendData.setStatus(bookEnum.ordinal());
        sendData.setMsg(bookEnum.toString());

        if (bookEnum == BookEnum.CHANGE_BOOK_STATUS_SUCCESS) {
            sendData.setData("");
        } else if (bookEnum == BookEnum.UNKNOWN_ERROR) {
            sendData.setData("");
        }

        out.write(sendData.toJson());
    }

    public BookEnum ChangeBookStatus(Book book) {
        BookService bookService = new BookServiceImpl();

        /**
         * 状态加1取模
         */
        book.setBkState((byte) ((book.getBkState() + 1) % 2));

        if (bookService.updateBook(book) == 1) {
            return BookEnum.CHANGE_BOOK_STATUS_SUCCESS;
        }

        return BookEnum.UNKNOWN_ERROR;
    }
}
