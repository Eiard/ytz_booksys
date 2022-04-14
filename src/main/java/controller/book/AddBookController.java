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
@WebServlet(name = "AddBookController", urlPatterns = {"/AddBookController"})
public class AddBookController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("content-type", "text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();

        String bkName = req.getParameter("bkName");
        String bkAuthor = req.getParameter("bkAuthor");
        String bkPress = req.getParameter("bkPress");
        double bkPrice = Double.parseDouble(req.getParameter("bkPrice"));
        int bkNum = Integer.parseInt(req.getParameter("bkName"));
        String bkImage = req.getParameter("bkImage");

        Book book = new Book();
        book.setBkName(bkName);
        book.setBkAuthor(bkAuthor);
        book.setBkPress(bkPress);
        book.setBkPrice(bkPrice);
        book.setBkNum(bkNum);
        book.setBkImageUrl("");

        BookEnum bookEnum = addBook(book, bkImage);

        ResponseDataMap sendData = new ResponseDataMap();
        sendData.setStatus(bookEnum.ordinal());
        sendData.setMsg(bookEnum.toString());

        out.write(sendData.toJson());
    }


    public BookEnum addBook(Book book, String bkImage) {
        BookService bookService = new BookServiceImpl();
        int status = bookService.addBook(book);
        if (status == 1) {
            List<Book> books = bookService.fuzzyQueryAllBook(book.getBkName(), book.getBkAuthor(), book.getBkPress());

            int bkId = books.get(0).getBkId();
            /**
             * 处理图片
             */


            return BookEnum.ADD_BOOK_SUCCESS;
        } else if (status == 2) {
            return BookEnum.ADD_SAME_BOOK_ERROR;
        }
        return BookEnum.UNKNOWN_ERROR;
    }


}
