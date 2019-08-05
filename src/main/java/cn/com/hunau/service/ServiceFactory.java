package cn.com.hunau.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ServiceFactory {
    //定义全局工厂对象
    private static ServiceFactory factory;
    private Properties properties;

    /**
     * 私有化构造器
     */
    private ServiceFactory() {
        init();
    }

    public static ServiceFactory buildServiceFactory() {
        if (factory == null) {
            factory = new ServiceFactory();
        }
        return factory;
    }

    private void init() {
        //读取配置文件
        InputStream inputStream = ServiceFactory.class.getClassLoader().getResourceAsStream("service.properties");
        properties = new Properties();
        try {
            //加载配置文件流
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Object createObject(String clsName) {
        Object obj = null;
        // 加载类
        Class cls = null;
        try {
            cls = Class.forName(clsName);
            obj = cls.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        // 反射产生对象

        return obj;
    }

    public ArticleService createArticleService() {
        ArticleService service = null;
        try {
            service = (ArticleService) this.createObject(this.properties.getProperty("articleService"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return service;
    }

    public UserService createUserService() {
        UserService service = null;
        try {
            // 创建UserService对象（new 配置文件中UserService对应的实现类）
            service = (UserService) this.createObject(this.properties.getProperty("userService"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return service;
    }

    public DetailArticleService createDetailArticleService() {
        DetailArticleService service = null;
        try {
            service = (DetailArticleService) this.createObject(this.properties.getProperty("detailArticleService"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return service;
    }

    public MessageService createMessageService() {
        MessageService service = null;
        try {
            service = (MessageService) this.createObject(this.properties.getProperty("messageService"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return service;
    }

    public static void main(String[] args) {
        MessageService service = ServiceFactory.buildServiceFactory().createMessageService();
        System.out.println(service);
    }
}
