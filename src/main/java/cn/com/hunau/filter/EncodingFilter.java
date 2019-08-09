package cn.com.hunau.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class EncodingFilter implements Filter {
    private String sourceEncoding = "gb2312";
    private String targetEncoding = "utf-8";
    private List<String> noFilterList = new ArrayList<String>();

    public void destroy() {
    }


    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        MyRequest request = new MyRequest((HttpServletRequest) req);
        resp.setContentType("text/html;charset=" + targetEncoding);//响应类型和编码
        String t = request.getParameter("username");
        //执行后面的资源
        chain.doFilter(request, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        String source = config.getInitParameter("sourceEncoding");
        String target = config.getInitParameter("targetEncoding");
        if (source != null) {
            this.sourceEncoding = source;
        }
        if (target != null) {
            this.targetEncoding = target;
        }
        String noFilterString = config.getInitParameter("noFilter");
        if (noFilterString != null) {
            String[] strs = noFilterString.split(",");
            if (strs != null) {
                for (String str : strs) {
                    this.noFilterList.add(str);
                }
            }
        }
    }

    //请求包装器
    private class MyRequest extends HttpServletRequestWrapper {

        public MyRequest(HttpServletRequest request) {
            super(request);
        }

        public String getParameter(String name) {
            String value = super.getParameter(name);
            if (value != null) {
                value = convertToOK(value);
            }
            return value;
        }

        private String convertToOK(String value) {
            if (value != null) {
                byte[] bytes = new byte[0];
                try {
                    bytes = value.getBytes(sourceEncoding);
                    value = new String(bytes, targetEncoding);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            return value;
        }

        private boolean needFilter() {
            boolean bool = false;

            String reqName = super.getRequestURI();
            int index = reqName.lastIndexOf("/");
            if (index != -1) {
                reqName = reqName.substring(index + 1);
                bool = !noFilterList.contains(reqName);
            }
            return bool;
        }
    }

}
