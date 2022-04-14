package controller.book;

import Utils.ResponseDataMap;
import controller.controllerEnum.BookEnum;
import service.book.BookService;
import service.book.BookServiceImpl;

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

        int bkId = Integer.parseInt(req.getParameter("bkId"));

        BookEnum bookEnum = ChangeBookStatus(bkId);

        ResponseDataMap sendData = new ResponseDataMap();
        sendData.setStatus(bookEnum.ordinal());
        sendData.setMsg(bookEnum.toString());

        if (bookEnum == BookEnum.CHANGE_BOOK_STATUS_SUCCESS) {
            sendData.setData("");
        } else if (bookEnum == BookEnum.CHANGE_BOOK_BKID_NOT_EXIST_ERROR) {
            sendData.setData("");
        } else if (bookEnum == BookEnum.UNKNOWN_ERROR) {
            sendData.setData("");
        }

        out.write(sendData.toJson());
    }

    public BookEnum ChangeBookStatus(int bkId) {
        BookService bookService = new BookServiceImpl();

        int status = bookService.changeBookStatus(bkId);

        if (status == 1) {
            return BookEnum.CHANGE_BOOK_STATUS_SUCCESS;
        } else if (status == 2) {
            return BookEnum.CHANGE_BOOK_BKID_NOT_EXIST_ERROR;
        }

        return BookEnum.UNKNOWN_ERROR;
    }
}
