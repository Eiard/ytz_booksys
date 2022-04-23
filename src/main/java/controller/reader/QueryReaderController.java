package controller.reader;

import Utils.FastJsonUtils;
import Utils.ResponseDataMap;
import com.alibaba.fastjson.TypeReference;
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
import java.util.ArrayList;
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

        List<Reader> readers;
        ReaderEnum readerEnum;
        int rdId;

        String status = req.getParameter("status");
        ResponseDataMap sendData = new ResponseDataMap();

        if ("0".equals(status)) {
            try {
                rdId = FastJsonUtils.strToJavaBean(req.getParameter("rdId"), new TypeReference<>() {
                });
            } catch (Exception e) {
                readerEnum = ReaderEnum.QUERY_STATUS_ERROR;
                sendData.setStatus(readerEnum.ordinal());
                sendData.setMsg(readerEnum.toString());
                out.write(sendData.toJson());
                return;
            }
            readerEnum = ReaderEnum.QUERY_RDID_SUCCESS;
            readers = queryReaderById(rdId);
            sendData.put("readers", readers);
        } else if ("1".equals(status)) {
            readerEnum = ReaderEnum.QUERY_DEPT_RDNAME_SUCCESS;
            String rdInfo = req.getParameter("rdInfo");
            readers = fuzzyQueryReaderList(rdInfo);
            sendData.put("readers", readers);
        } else {
            readerEnum = ReaderEnum.QUERY_STATUS_ERROR;
        }

        sendData.setStatus(readerEnum.ordinal());
        sendData.setMsg(readerEnum.toString());

        out.write(sendData.toJson());
    }


    /**
     * rdInfo
     * 部门 姓名 模糊查询
     *
     * @param rdInfo
     * @return List<Reader>
     */
    public List<Reader> fuzzyQueryReaderList(String rdInfo) {
        ReaderService readerService = new ReaderServiceImpl();
        return readerService.fuzzyQueryReaderList(rdInfo);
    }

    /**
     * rdId
     * 学号具体查询
     *
     * @param rdId
     * @return List<Reader>
     */
    public List<Reader> queryReaderById(int rdId) {
        ReaderService readerService = new ReaderServiceImpl();
        List<Reader> readers = new ArrayList<>();
        readers.add(readerService.queryOneReader(rdId));
        return readers;
    }

}
