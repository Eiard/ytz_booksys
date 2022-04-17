package controller.book;

import Utils.FastJsonUtils;
import Utils.ResponseDataMap;
import com.alibaba.fastjson.TypeReference;
import controller.controllerEnum.BookEnum;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import pojo.Book;
import service.book.BookService;
import service.book.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * # -*- coding:utf-8 -*- #
 * 作者:30671
 * 日期:2022年04月14日21时30分
 */
@WebServlet(name = "AddBookController", urlPatterns = {"/AddBookController"})
public class AddBookController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("content-type", "text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();

        /**
         * 默认未知错误
         */
        BookEnum bookEnum = BookEnum.UNKNOWN_ERROR;
        if (ServletFileUpload.isMultipartContent(req)) {      //如果对象是multipart请求
            try {
                bookEnum = addBook(req);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * 回复数据封装
         */
        ResponseDataMap sendData = new ResponseDataMap();
        sendData.setStatus(bookEnum.ordinal());
        sendData.setMsg(bookEnum.toString());

        out.write(sendData.toJson());
    }


    public BookEnum addBook(HttpServletRequest req) throws Exception {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("utf-8");

        InputStream inputStream = null;
        OutputStream outputStream1 = null;
        OutputStream outputStream2 = null;

        FileItem imageItem = null;
        Book book = new Book();
        // 提取数据
        List<FileItem> fileItems = upload.parseRequest(req);

        for (FileItem item : fileItems) {
            if (item.isFormField()) {     //如果item为普通表单
                //这里获得的是普通的表单，即普通键值对，存在汉语字符串编码问题
                String key = item.getFieldName();   //键
                String jsonString = item.getString();     //值
                if (key.equals("book")) {
                    book = FastJsonUtils.strToJavaBean(jsonString, new TypeReference<Book>() {
                    });
                }
            } else {  // 找到图片信息 临时取出来
                imageItem = item;
            }
        }

        BookService bookService = new BookServiceImpl();
        int status = bookService.addBook(book);
        if (status == 1) {
            List<Book> books = bookService.fuzzyQueryAllBook(book.getBkName(), book.getBkAuthor(), book.getBkPress());

            Integer bkId = books.get(0).getBkId();

            /**
             * 处理图片
             */
            String fileName = bkId.toString();
            System.out.println("**=>" + fileName);
            if (imageItem == null) {
                return BookEnum.ADD_BOOK_IMAGE_ERROR;
            }
            inputStream = imageItem.getInputStream();

            /**
             * 分别为
             *      动态地址(Tomcat运行的静态资源地址)
             *      静态地址(代码区)       每次启动程序会将静态地址的图片加载到动态区
             *      图片双向拷贝
             */
            String dynamicPath = this.getServletContext().getRealPath("/BookImage/");
            String staticPath = "D:/Computer/Code/Java/BookServer/src/main/webapp/BookImage/";
            File dynamicFile = new File(dynamicPath, fileName);
            File staticFile = new File(staticPath, fileName);

            int len = -1;
            byte[] buf = new byte[1024];

            outputStream1 = new FileOutputStream(dynamicFile);

            while ((len = inputStream.read(buf)) != -1) {
                outputStream1.write(buf, 0, len);
            }

            outputStream2 = new FileOutputStream(staticFile);
            len = -1;
            buf = new byte[1024];
            while ((len = inputStream.read(buf)) != -1) {
                outputStream2.write(buf, 0, len);
            }

            return BookEnum.ADD_BOOK_SUCCESS;
        } else if (status == 2) {
            return BookEnum.ADD_SAME_BOOK_ERROR;
        }
        return BookEnum.UNKNOWN_ERROR;
    }


}
