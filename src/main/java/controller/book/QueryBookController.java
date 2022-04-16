package controller.book;

import Utils.ResponseDataMap;
import controller.controllerEnum.BookEnum;
import pojo.Book;
import service.book.BookService;
import service.book.BookServiceImpl;

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
 * 日期:2022年04月14日21时30分
 */
@WebServlet(name = "QueryBookController", urlPatterns = {"/QueryBookController"})
public class QueryBookController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("content-type", "text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();

        String bkInfo = req.getParameter("bkInfo");

        List<Book> books = queryBookList(bkInfo);

        BookEnum bookEnum;
        if (books.size() == 0) {
            bookEnum = BookEnum.QUERY_NO_BOOK_ERROR;
        } else {
            bookEnum = BookEnum.QUERY_SUCCESS;
        }

        /**
         * 回复数据封装
         */
        ResponseDataMap sendData = new ResponseDataMap();
        sendData.setStatus(bookEnum.ordinal());
        sendData.setMsg(bookEnum.toString());
        sendData.setData(books);

        out.write(sendData.toJson());
    }

    public List<Book> queryBookList(String bkInfo) {
        BookService bookService = new BookServiceImpl();

        return bookService.fuzzyQueryAllBook(bkInfo);
    }

}
