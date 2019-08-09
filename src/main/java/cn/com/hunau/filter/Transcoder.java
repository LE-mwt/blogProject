package cn.com.hunau.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet Filter implementation class Transcoder
 */
//@WebFilter(urlPatterns = "/*", initParams = {@WebInitParam(name = "noFilter", value = "updatePersonServlet")})
public class Transcoder implements Filter {
    private String charis;
    private String charut;
    private List<String> noFilterList = new ArrayList<String>();

    /**
     * Default constructor.
     */
    public Transcoder() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub
    }


    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // TODO Auto-generated method stub
        // place your code here
        MyTranscoding req = new MyTranscoding((HttpServletRequest) request);
        response.setContentType("text/html;charset=UTF-8");
        // pass the request along the filter chain
        String str = req.getParameter("name");
        chain.doFilter(req, response);
    }


    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
        charis = fConfig.getInitParameter("char1");
        charut = fConfig.getInitParameter("char2");
        String noFilterString = fConfig.getInitParameter("noFilter");
        if (noFilterString != null) {
            String[] strs = noFilterString.split(",");
            if (strs != null) {
                for (String str : strs) {
                    this.noFilterList.add(str);
                }
            }
        }
    }

    private String Transcoding(String value) {
        if (value != null) {
            try {
                byte[] bytes = value.getBytes("iso-8859-1");
                value = new String(bytes, "utf-8");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        return value;
    }

    private class MyTranscoding extends HttpServletRequestWrapper {

        public MyTranscoding(HttpServletRequest req) {
            super(req);
        }

        public String getParameter(String name) {

            String value = super.getParameter(name);
//            System.out.println("****************" + value);
            if (value != null && needFilter()) {
                value = Transcoding(value);
            }
//            System.out.println("****************" + value);
            return value;
        }

        public String[] getParameterValues(String name) {
            String[] values = super.getParameterValues(name);
            if (values != null && needFilter()) {
                for (int i = 0; i < values.length; i++) {
                    values[i] = Transcoding(values[i]);
                }
            }
            return values;
        }

        private boolean needFilter() {
            boolean bool = false;

            String reqName = super.getRequestURI();
            int index = reqName.lastIndexOf("/");
            if (index != -1) {
                reqName = reqName.substring(index + 1);
            }
            index = reqName.indexOf("?");
            if (index != -1) {
                reqName = reqName.substring(0, index);
            }
            bool = !noFilterList.contains(reqName);

            return bool;
        }

    }
}

