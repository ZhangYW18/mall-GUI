/*
 * @author VampireWeekend
 * @date 19-4-8 下午7:29
 */

package com.vampire.utils;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

import com.vampire.utils.*;

public class OrderCommodityUtils {
    private JdbcUtils jdbcUtils;
    private String[] tableStrings = {"orderID", "commodityID", "commodityName", "count", "price"};

    public OrderCommodityUtils() {
        jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
    }

    // 获得所有订单信息
    // done

    public List<Map<String, Object>> findAllOrderCommodity() {
        String sql = "select * from OrderCommodity";

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            list = jdbcUtils.findModeResult(sql, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // done
    // 根据商品查找订单信息,多态

    public List<Map<String, Object>> searchOrderCommodity(String commodityID) {
        String sql = "select * from OrderCommodity where commodityID LIKE ?";

        List<Object> params = new ArrayList<Object>();
        params.add(commodityID);

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            list = jdbcUtils.findModeResult(sql, params);
            // System.out.println(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 根据ID查找订单信息,多态
    //done

    public List<Map<String, Object>> searchOrderCommodity(int orderID) {
        String sql = "select * from OrderCommodity where orderID = " + orderID;

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            list = jdbcUtils.findModeResult(sql, null);
            // System.out.println(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //查找订单信息,多态
    //done
    public Map<String, Object> searchOrderCommodity(int orderID, String commodityID) {
        String sql = "select * from OrderCommodity where commodityID LIKE ? and orderID = " + orderID;

        List<Object> params = new ArrayList<Object>();
        params.add(commodityID);

        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map = jdbcUtils.findSimpleResult(sql, params);
            // System.out.println(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    // 增加一个客户
    // done
    public boolean addOrderCommodity(Map<String, Object> row) {
        String sql = "insert into OrderCommodity values (?, ?, ?, ?, ?)";
        List<Object> params = new ArrayList<Object>();

        for (int i=0;i<tableStrings.length;i++) {
            params.add(row.get(tableStrings[i]));
        }

        boolean flag = false;
        try {
            flag = jdbcUtils.updateByPreparedStatement(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    //删除一个订单商品
    //done

    public boolean removeOrderCommodity(int orderID, String commodityID) {
        String sql = "delete from OrderCommodity where commodityID LIKE ? and orderID = " + orderID;

        List<Object> params = new ArrayList<Object>();
        params.add(commodityID);

        boolean flag = false;
        try {
            flag = jdbcUtils.updateByPreparedStatement(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }


    // 更新数量
    // done

    public boolean addCommodityCount(int count, int orderID, String commodityID) {
        String sql = "";
        sql = "update OrderCommodity set count = count + " + count + " where commodityID LIKE ? and orderID = " + orderID;

        List<Object> params = new ArrayList<Object>();
        params.add(commodityID);

        boolean flag = false;
        try {
            flag = jdbcUtils.updateByPreparedStatement(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }


}