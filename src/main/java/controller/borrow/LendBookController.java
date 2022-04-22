package controller.borrow;

import Utils.FastJsonUtils;
import Utils.ResponseDataMap;
import com.alibaba.fastjson.TypeReference;
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
@WebServlet(name = "LendBookController", urlPatterns = {"/LendBookController"})
public class LendBookController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("content-type", "text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();

        List<Integer> bkIds;
        Integer rdId;
        int readerTypeDayNum;

        BorrowEnum borrowEnum = BorrowEnum.LEND_SUCCESS;
        ResponseDataMap sendData = new ResponseDataMap();
        try {
            rdId = FastJsonUtils.strToJavaBean(req.getParameter("rdId"), new TypeReference<>() {
            });
            bkIds = FastJsonUtils.strToJavaBeanList(req.getParameter("bkIds"), new TypeReference<>() {
            });
            readerTypeDayNum = Integer.parseInt(req.getParameter("readerTypeDayNum"));
        } catch (Exception e) {
            borrowEnum = BorrowEnum.RETURN_FORMAT_ERROR;
            sendData.setStatus(borrowEnum.ordinal());
            sendData.setMsg(borrowEnum.toString());
            out.write(sendData.toJson());
            return;
        }

        BorrowService borrowService = new BorrowServiceImpl();
        List<Borrow> borrows = borrowService.buildBorrowList(rdId, bkIds);

        /**
         * 传过来没有一本书
         */
        if (borrows == null) {
            borrowEnum = BorrowEnum.LEND_NONE_BOOK_ERROR;
            sendData.setStatus(borrowEnum.ordinal());
            sendData.setMsg(borrowEnum.toString());
            out.write(sendData.toJson());
        }

        /**
         * 一次性多次借书 返回对应状态
         */
        List<Boolean> booleans = LendBooks(borrows, readerTypeDayNum);

        /**
         * 回复数据封装
         */
        sendData.setData(booleans);
        sendData.setStatus(borrowEnum.ordinal());
        sendData.setMsg(borrowEnum.toString());
        out.write(sendData.toJson());
    }

    public List<Boolean> LendBooks(List<Borrow> borrows, int readerTypeDayNum) {
        BorrowService borrowService = new BorrowServiceImpl();
        return borrowService.lendBorrows(borrows, readerTypeDayNum);
    }
}
