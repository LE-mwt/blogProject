package cn.com.hunau.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DAOFactory {
    //定义全局工厂对象
    private static DAOFactory factory;
    private Properties properties;

    /**
     * 私有化构造器
     */
    private DAOFactory() {
        init();
    }

    public static DAOFactory buildDAOFactory() {
        if (factory == null) {
            factory = new DAOFactory();
        }
        return factory;
    }

    private void init() {
        //读取配置文件
        InputStream inputStream = DAOFactory.class.getClassLoader().getResourceAsStream("dao.properties");
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

    public UserDAO createUserDAO() {
        UserDAO dao = null;
        try {
            // 创建IFoodDAO对象（new 配置文件中foodDAO对应的实现类）
            dao = (UserDAO) this.createObject(this.properties.getProperty("userDAO"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return dao;
    }

    public ArticleDAO createArticleDAO() {
        ArticleDAO dao = null;
        try {
            // 创建IFoodDAO对象（new 配置文件中foodDAO对应的实现类）
            dao = (ArticleDAO) this.createObject(this.properties.getProperty("articleDAO"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return dao;
    }

    public AdminDAO createAdminDAO() {
        AdminDAO dao = null;
        try {
            // 创建IFoodDAO对象（new 配置文件中foodDAO对应的实现类）
            dao = (AdminDAO) this.createObject(this.properties.getProperty("adminDAO"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return dao;
    }

    public CommentDAO createCommentDAO() {
        CommentDAO dao = null;
        try {
            // 创建IFoodDAO对象（new 配置文件中foodDAO对应的实现类）
            dao = (CommentDAO) this.createObject(this.properties.getProperty("commentDAO"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return dao;
    }

    public FansDAO createFansDAO() {
        FansDAO dao = null;
        try {
            // 创建IFoodDAO对象（new 配置文件中foodDAO对应的实现类）
            dao = (FansDAO) this.createObject(this.properties.getProperty("fansDAO"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return dao;
    }


}
