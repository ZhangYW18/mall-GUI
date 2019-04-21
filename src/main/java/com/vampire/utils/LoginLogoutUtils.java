package com.vampire.utils;

import java.util.ArrayList;
import java.util.List;
import com.vampire.utils.JdbcUtils;
import com.vampire.beans.*;


public class LoginLogoutUtils {

    private JdbcUtils jdbcUtils;

    public LoginLogoutUtils() {
        jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
    }

    /**
     * 登陆
     * @param
     * @return
     */
    public String login(Object object) {

        String resultStr = "登陆失败";

        String username = null;
        String password = null;
        String sql = null;
        User admin = (User) object;
        username = admin.getUsername();
        password = admin.getPassword();
        sql = "select * from User where username = ? and password = ?";
        List<Object> params = new ArrayList<Object>();
        params.add(username);
        params.add(password);
        try {
            User databaseAdmin =  jdbcUtils.findSimpleRefResult(sql, params, User.class);
            if (databaseAdmin != null) {
 //               Session.userInfo = databaseAdmin;
                resultStr = "登陆成功";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultStr;

    }

    /**
     * 登出
     */
    public void logout() {
 //       Session.userInfo = null;
    }





    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if (jdbcUtils != null) {
            jdbcUtils.releaseConn();
            jdbcUtils = null;

        }
        System.out.println(this.getClass().toString()+"销毁了");
    }
}
