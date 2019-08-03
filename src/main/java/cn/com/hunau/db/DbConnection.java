package cn.com.hunau.db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class DbConnection {

    private static DbConnection dbCon = null;
    private String url;
    private String username;
    private String password;

    private DbConnection() {
        try {
            this.init();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static DbConnection getInstance() {
        if (dbCon == null)
            dbCon = new DbConnection();
        return dbCon;
    }

    private void init() throws Exception {
        Properties props = new Properties();
        InputStream in = DbConnection.class.getClassLoader().getResourceAsStream("db.properties");
        props.load(in);
        this.url = props.getProperty("url");
        this.username = props.getProperty("username");
        this.password = props.getProperty("password");

        Class.forName(props.getProperty("driverName"));
    }

    public Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void close(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
            if (conn != null)
                conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Connection conn = DbConnection.getInstance().getConnection();
        if (conn != null) {
            System.out.println("连接成功");
        } else {
            System.out.println("连接失败");
        }
    }
}


