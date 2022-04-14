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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("content-type", "text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();

        String bkName = req.getParameter("bkName");
        String bkAuthor = req.getParameter("bkAuthor");
        String bkPress = req.getParameter("bkPress");

        List<Book> books = queryBookList(bkName, bkAuthor, bkPress);

        ResponseDataMap sendData = new ResponseDataMap();
        sendData.setStatus(BookEnum.QUERY_SUCCESS.ordinal());
        sendData.setMsg(BookEnum.QUERY_SUCCESS.toString());
        sendData.setData(books);

        out.write(sendData.toJson());
    }

    public List<Book> queryBookList(String bkName, String bkAuthor, String bkPress) {
        BookService bookService = new BookServiceImpl();

        return bookService.fuzzyQueryAllBook(bkName, bkAuthor, bkPress);
    }

}
