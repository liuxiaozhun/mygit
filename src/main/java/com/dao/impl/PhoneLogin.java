package com.dao.impl;

import com.dao.ILogin;
import com.pojo.User;
import com.util.DBUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PhoneLogin implements ILogin {

    public static void main(String[] args) throws ClassNotFoundException {
        //获取类的类型1
        Class cls = User.class;
        //获取类的类型1
        Class cls2 = Class.forName("com.pojo.User");
    }

    public static void main1(String[] args) throws Exception{
        //获取类的类型
        Class cls = User.class;
        //可以通过类型获取该类中的大部分信息
        //1. 可以获取该类的全路径和名称
        System.out.println(cls.getName());
        //2. 可以创建该类的对象
        Object obj = cls.newInstance();
        //3. 可以获取该类的所有属性对象数组 (公有的)
//        Field[] fds = cls.getFields();
        //4. 获取所有字段
//        Field[] fds = cls.getDeclaredFields();
//        System.out.println("len:"+fds.length);
//        for (int i = 0; i < fds.length; i++) {
//            System.out.println("名字："+fds[i].getName());
//            System.out.println("类型："+fds[i].getType());
//        }
        //5. 获取某个一具体的属性对象
        Field fd = cls.getDeclaredField("age");
        System.out.println("名字："+fd.getName());
        System.out.println("类型："+fd.getType());
        //6. 可以给实例化的某个属性赋值(要先有对象)
        fd.set(obj,18);

        //7. 获取方法（公有方法/所有方法）
//        Method[] ms = cls.getMethods();//所有方法，包括父类方法
        Method[] ms = cls.getDeclaredMethods();
        for (int i = 0; i < ms.length; i++) {
            System.out.println(ms[i].getName());
            //循环判断方法名，找到setName方法的对象
            if(ms[i].getName().equals("setName")){
                //调用刚才25行创建的实例obj的该方法，设置姓名
                ms[i].invoke(obj,"鲁班");
            }
        }
        System.out.println(obj);
    }

    @Override
    public User login(User u) {
        String sql = "select * from t_user where name = ? and pass = ? and state = 0";
        List<User> list = DBUtil.comQuery(sql,User.class,u.getName(),u.getPass());
        if(list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public int reg(User u) {
        String sql = "insert t_user values(0,?,?,?)";
        return DBUtil.comUp(sql,"","","");
    }

    @Override
    public List<User> getAllUser() {
        String sql = "select * from t_user";
        Connection conn = DBUtil.getConn();
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<User> list = new ArrayList<User>();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                User u = new User();
                u.setId(rs.getInt(1));
                u.setName(rs.getString(2));
                list.add(u);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.free(conn,ps,rs);
        }

        return list;
    }
}
