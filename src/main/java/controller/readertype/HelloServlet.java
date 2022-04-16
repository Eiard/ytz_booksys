package controller.readertype;

import java.io.*;
import java.util.List;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet(name = "helloServlet", value = "/dome")
public class HelloServlet extends HttpServlet {
    private String message;

    @Override
    public void init() {
        message = "Hello World!";
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setHeader("content-type", "text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.println("ok!");

        if (ServletFileUpload.isMultipartContent(req)) {      //如果对象是multipart请求
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding("utf-8");

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                List<FileItem> fileItems = upload.parseRequest(req);

                for (FileItem item : fileItems) {
                    if (item.isFormField()) {     //如果item为普通表单
                          //这里获得的是普通的表单，即普通键值对，存在字符串编码问题
                        String fieldName = item.getFieldName();   //键
                        String fieldValue = item.getString();     //值

                        System.out.println("=>"+fieldName+" : "+fieldValue);

                    } else {
                        String fileName = item.getName();     //这里获得的是文件名
                        System.out.println("**=>"+fileName);
                        inputStream = item.getInputStream();
                        String path = this.getServletContext().getRealPath("/");     //这里是文件存放的路径，相对于静态资源为根路径，如果添加文件夹，须确保该文件夹存在
                        File file = new File(path, fileName);
                        outputStream = new FileOutputStream(file);
                        int len = -1;
                        byte[] buf = new byte[1024];
                        while ((len = inputStream.read(buf)) != -1) {
                            outputStream.write(buf, 0, len);
                        }


                    }
                }
            }catch (EOFException | FileUploadException e){
                e.printStackTrace();
            }
        }




    }

}