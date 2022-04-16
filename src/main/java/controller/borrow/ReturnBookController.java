package controller.borrow;

import controller.controllerEnum.BorrowEnum;
import pojo.Borrow;

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
@WebServlet(name = "ReturnBookController", urlPatterns = {"/ReturnBookController"})
public class ReturnBookController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    public BorrowEnum ReturnBooks(List<Borrow> borrows){






        return BorrowEnum.
    }





}
