package controller.borrow;

import pojo.Book;
import pojo.Borrow;
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
import java.util.List;

/**
 * # -*- coding:utf-8 -*- #
 * 作者:30671
 * 日期:2022年04月16日10时12分
 */
@WebServlet(name = "LendBookController", urlPatterns = {"/LendBookController"})
public class LendBookController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    public List<Boolean> LendBooks(List<Borrow> borrows) {

        BorrowService borrowService = new BorrowServiceImpl();

        return borrowService.lendBorrows(borrows);
    }

}
