package com.util;

import com.dao.ILogin;

import java.lang.reflect.Field;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {
    private static final String driverClass = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/db2101?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "12345678";


    private DBUtil() {

    }
    static {
        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /*public static void main(String[] args) {
        String sql = "select * from t_user";
        List<User> list = comQuery(sql, User.class);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getName());
        }
    }*/
    /**
     * 通用查询
     * @param sql
     * @param pa
     * @return
     */
    public static<X> List<X> comQuery(String sql,Class<X> cls, Object... pa){
        List<X> list = new ArrayList<X>();

        Connection conn = getConn();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < pa.length; i++) {
                ps.setObject(i+1,pa[i]);
            }
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                /*User u = new User();
                u.setName(rs.getString("name"));
                list.add(u);*/
                //创建对象
                Object obj = cls.newInstance();
                //获取该对象的所有属性数组（对象）
                Field[] fds = cls.getDeclaredFields();
                for (int i = 0; i < fds.length; i++) {
                    //获取某个属性的名字
                    String pn = fds[i].getName();

                    //获取该属性在结果集中某一行的值 类似于rs.getString("name")
                    Object obj_f = rs.getObject(pn);
                    System.out.println("name:"+pn+"___val:"+obj_f);
                    boolean flag = true;
                    String sdate = "";
                    if(obj_f instanceof LocalDateTime){
                        System.out.println("LocalDateTime");
                        //把数据库查询出来的内容，转为string
                        LocalDateTime ldt = (LocalDateTime)obj_f;
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        sdate = ldt.format(dtf);
                        flag = false;
                    }
                    System.out.println("__________________");
                    //设置对象的属性值
                    fds[i].setAccessible(true);//设置可以直接修改私有属性,才能直接赋值
                    if(flag){
                        fds[i].set(obj,obj_f);   //相当于  user.name = "鲁班";
                    }else{
                        fds[i].set(obj,sdate);
                    }

                    //返回该属性的访问修饰符 public...
//                    int tp = fds[i].getModifiers();
//                    System.out.println(Modifier.toString(tp));
                }
                //把对象添加到结果集
                list.add((X)obj);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            free(conn,ps);
        }

        return list;
    }

    /**
     * 通用增删改
     * @param sql   SQL 语句
     * @param pa    参数列表
     * @return
     */
    public static int comUp(String sql,String... pa){
        Connection conn = getConn();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < pa.length; i++) {
                ps.setString(i+1,pa[i]);
            }
            int ret = ps.executeUpdate();
            if(ret > 0){
                return ILogin.COM_OK;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            free(conn,ps);
        }

        return ILogin.COM_ERROR;
    }

    public static Connection getConn() {
        Connection conn = null;
        try {

            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("conn......");
        return conn;
    }

    public static PreparedStatement getPreparedStatement(Connection conn, String sql){
        PreparedStatement ps=null;
        try {
            ps = conn.prepareStatement(sql);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ps;
    }


    public static void free(Connection conn, PreparedStatement ps, ResultSet rs) {

        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static void free(Connection conn, PreparedStatement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public static void bindParam(PreparedStatement ps,Object... params) {
        for (int i=1;i<= params.length;i++){
            try {
                ps.setObject(i,params[i-1]);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}

