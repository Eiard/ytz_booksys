package controller.reader;

import Utils.ResponseDataMap;
import controller.controllerEnum.ReaderEnum;
import pojo.Reader;
import service.reader.ReaderService;
import service.reader.ReaderServiceImpl;

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
 * 日期:2022年04月21日23时00分
 */
@WebServlet(name = "QueryReaderController", urlPatterns = {"/QueryReaderController"})
public class QueryReaderController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("content-type", "text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();

        String rdInfo = req.getParameter("rdInfo");

        List<Reader> readers;
        ReaderEnum readerEnum;

        ResponseDataMap sendData = new ResponseDataMap();

        /**
         * 先判断是不是学号
         */
        try {
            Integer rdId = Integer.parseInt(rdInfo);
            /**
             * 是学号在这处理
             */
            readers = fuzzyQueryReaderList(rdInfo, rdInfo);
            readerEnum = ReaderEnum.QUERY_IS_RDID_SUCCESS;
        } catch (Exception e) {
            /**
             * 不是学号在这处理
             */
            readers = fuzzyQueryReaderList(rdInfo, "");
            readerEnum = ReaderEnum.QUERY_IS_NOT_RDID_SUCCESS;
        }

        sendData.setStatus(readerEnum.ordinal());
        sendData.setMsg(readerEnum.toString());
        sendData.put("readers", readers);
        out.write(sendData.toJson());
    }


    /**
     * rdInfo
     * 可以是学号 部门 姓名
     *
     * @param rdInfo
     * @return List<Reader>
     */
    public List<Reader> fuzzyQueryReaderList(String rdInfo, String rdId) {
        ReaderService readerService = new ReaderServiceImpl();
        return readerService.fuzzyQueryReaderList(rdInfo, rdId);
    }
}
