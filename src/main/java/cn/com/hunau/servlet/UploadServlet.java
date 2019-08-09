package cn.com.hunau.servlet;

import cn.com.hunau.vo.ArticleVo;
import org.apache.commons.fileupload.FileItem;
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
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

/**
 * 从混合表单中得到数据封装到ArticleVo中 并传给添加文章的Servlet
 */
public class UploadServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer user_id = (Integer) req.getSession().getAttribute("user_id");
        //构造一个文章vo
        ArticleVo articleVo = new ArticleVo();
        // 判断是不是上传的form
        boolean bool = ServletFileUpload.isMultipartContent(req);
        if (bool) {
            // fileItem的工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
//            // 指定临时目录
//            factory.setRepository(new File("e:/temp"));
            // 创建请求解析对象
            ServletFileUpload upload = new ServletFileUpload(factory);
            try {
                // 解析请求
                List<FileItem> items = upload.parseRequest(req);
                for (FileItem item : items) {
                    // 判断是否是普通的表单field
                    if (item.isFormField()) {
                        // 普通的表单数据
                        if (item.getFieldName().equals("article_title")) {
                            articleVo.setArticle_title(convertToOK(item.getString()));
                        } else if (item.getFieldName().equals("article_type")) {
                            articleVo.setArticle_type(convertToOK(item.getString()));
                        } else if (item.getFieldName().equals("article_context")) {
                            articleVo.setArticle_context(convertToOK(item.getString()));
                        } else if (!item.getFieldName().equals("article_private")) {
                            articleVo.setArticle_private(0);
                        } else if (item.getFieldName().equals("article_private")) {
                            articleVo.setArticle_private(1);
                        }
                    } else {
                        // 文件
                        // 获取文件名
                        String fileName = convertToOK(item.getName());
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
//                            System.out.println(item.getFieldName() + ">" + file.getName() + "***********" + file.getAbsolutePath());
                            resp.setContentType("charset=utf-8");
                            PrintWriter out = resp.getWriter();
                            //得到图片的url
                            String imgUrl = "upload/images/" + fileName;
                            articleVo.setArticle_cover(imgUrl);
//                            out.print(imgUrl);
//                            out.flush();
//                            out.close();
                        }
                    }
                }
                articleVo.setUser_id(user_id);
                System.out.println(articleVo);
                req.setAttribute("articleVo", articleVo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("不是上传文件的form");
        }
        req.getRequestDispatcher("/addArticleServlet?math=" + Math.random()).forward(req, resp);
    }

    private String convertToOK(String value) {
        if (value != null) {
            byte[] bytes = new byte[0];
            try {
                bytes = value.getBytes("iso-8859-1");
                value = new String(bytes, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return value;
    }

}
