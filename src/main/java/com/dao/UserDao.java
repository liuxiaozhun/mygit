package com.dao;

import com.bean.User;
import com.util.DBHelp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserDao {

    /**
     * 查询表中最大id
     *
     * @return返回最大id 如果表为空返回-1
     */
    private int maxId() {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Connection con = DBHelp.getCon();
        String id = "-1";
        try {
            pstm = con.prepareStatement("SELECT MAX(`id`)'id' FROM `IUser`");
            rs = pstm.executeQuery();
            while (rs.next()) {
                id = rs.getString("id");
            }
            return new Integer(id);
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            DBHelp.closeCon(con, pstm);
        }
        return -1;
    }

//    /**
//     *
//     * @param u
//     * @return
//     */
//    public boolean add(User u) {
//        /*
//        获得表中最大id
//         */
//        int i = maxId();
//        int id;
//        if (i == -1) {
//            id = 0;
//        } else {
//            id = i + 1;
//        }
//        PreparedStatement prsm = null;
//        int rs = 0;
//        Connection con = DBHelp.getCon();
//        try {
//            prsm = con.prepareStatement("INSERT INTO `IUser`(`id`,`uname`,`upass`) VALUE (?,?,?)");
//            prsm.setObject(1, id);
//            prsm.setObject(2, u.getName());
//            prsm.setObject(3, u.getPass());
//            rs = prsm.executeUpdate();
//            if (rs > 0) {
//                return true;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            DBHelp.closeCon(con, prsm);
//        }
//        return false;
//    }

//    public boolean select(User u){
//        PreparedStatement prsm = null;
//        Connection con = DBHelp.getCon();
//        ResultSet bl;
//        ArrayList arr = new ArrayList();
//        try {
//            prsm = con.prepareStatement("SELECT * FROM `IUser` WHERE (uname=? AND upass=?)");
//            prsm.setObject(1,u.getName());
//            prsm.setObject(2,u.getPass());
//            bl = prsm.executeQuery();
//            while (bl.next()){
//                User us = new User();
//                us.setName("uname");
//                us.setPass("upass");
//                arr.add(us);
//            }
//            if (arr.size()>0){
//                return true;
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            DBHelp.closeCon(con, prsm);
//        }
//        return false;
//    }

}
