package cn.com.hunau.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

public class UploadImgServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 判断是不是上传的form
        boolean bool = ServletFileUpload.isMultipartContent(req);
        if (bool) {
            // fileItem的工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // 创建请求解析对象
            ServletFileUpload upload = new ServletFileUpload(factory);
            try {
                // 解析请求
                List<FileItem> items = upload.parseRequest(req);
                for (FileItem item : items) {
                    // 文件
                    // 获取文件名
                    String fileName = item.getName();
                    //获取相对路径
                    ServletContext ctx = req.getServletContext();
                    String realPath = ctx.getRealPath("/upload/images");
                    // 写目标文件
                    if (fileName.contains(".")) {
                        //重命名文件
                        String replace = fileName.substring(0, fileName.lastIndexOf("."));
                        long time = new Date().getTime();
                        String newname = String.valueOf(time);
                        fileName = fileName.replace(replace, newname);

                        File file = new File(realPath + "/" + fileName);
                        item.write(file);
                        System.out.println(item.getFieldName() + ">" + file.getName() + "***********" + file.getAbsolutePath());
                        resp.setContentType("charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        //得到图片的url
                        String imgUrl = "upload/images/" + fileName;
                        out.print(imgUrl);
                        out.flush();
                        out.close();
                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
