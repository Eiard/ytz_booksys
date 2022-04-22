package controller.readertype;

import Utils.ResponseDataMap;
import controller.controllerEnum.ReaderEnum;
import controller.controllerEnum.ReaderTypeEnum;
import pojo.Reader;
import pojo.ReaderType;
import service.reader.ReaderService;
import service.reader.ReaderServiceImpl;
import service.readertype.ReaderTypeService;
import service.readertype.ReaderTypeServiceImpl;

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
 * 日期:2022年04月22日23时39分
 */
@WebServlet(name = "QueryReaderTypeController", urlPatterns = {"/QueryReaderTypeController"})
public class QueryReaderTypeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("content-type", "text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();

        List<ReaderType> readerTypes;
        ReaderTypeEnum readerTypeEnum;
        String typeName = req.getParameter("typeName");
        ResponseDataMap sendData = new ResponseDataMap();


        if (typeName == null){
            readerTypeEnum = ReaderTypeEnum.TYPENAME_FORMAT_ERROR;
        }

        readerTypes = queryReaderTypeByTypeName(typeName);

        if (readerTypes.size() == 0){
            readerTypeEnum = ReaderTypeEnum.QUERY_NONE_READERTYPE_ERROR;
        }else{
            readerTypeEnum = ReaderTypeEnum.QUERY_SUCCESS;
            sendData.put("readerTypes",readerTypes);
        }

        sendData.setStatus(readerTypeEnum.ordinal());
        sendData.setMsg(readerTypeEnum.toString());

        out.write(sendData.toJson());
    }


    /**
     * typeName
     * 读者类别名具体查询
     *
     * @param typeName
     * @return List<ReaderType>
     */
    public List<ReaderType> queryReaderTypeByTypeName(String typeName) {
        ReaderTypeService readerTypeService = new ReaderTypeServiceImpl();
        return readerTypeService.fuzzyQueryReaderType(typeName);
    }

}
