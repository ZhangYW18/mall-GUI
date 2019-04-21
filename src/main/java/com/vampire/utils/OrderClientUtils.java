/*
 * @author VampireWeekend
 * @date 19-4-7 下午10:14
 */

/*
 * @author VampireWeekend
 * @date 19-4-7 下午9:30
 */

package com.vampire.utils;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

import com.vampire.utils.*;

public class OrderClientUtils {
    private JdbcUtils jdbcUtils;
    private String[] tableStrings = {"orderID", "clientID", "clientName", "startTime",
            "money", "isPaid", "isSent", "sentTime", "hasReceipt"};

    public OrderClientUtils() {
        jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
    }

    // 获得所有订单信息
    // done

    public List<Map<String, Object>> findAllOrderClient() {
        String sql = "select * from OrderClient order by orderID desc";

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            list = jdbcUtils.findModeResult(sql, null);
            // System.out.println(list);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // done
    // 根据客户查找订单信息

    public List<Map<String, Object>> searchOrder(int clientID) {
        String sql = "select * from OrderClient where clientID = " + clientID;

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            list = jdbcUtils.findModeResult(sql, null);
            // System.out.println(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 根据商品查找订单信息
    public List<Map<String, Object>> searchOrder(String commodityID) {

        String sql = "select * from OrderClient where orderID in " +
                "(select orderID from OrderCommodity where commodityID = ?)";
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

    // done
    //根据ID查找订单信息
    public Map<String, Object> searchOrderClientByOrderID(int orderID) {
        String sql = "select * from OrderClient where orderID = " + orderID;

        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map = jdbcUtils.findSimpleResult(sql, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    //增加一个客户
    //done
    public boolean addOrderClient(Map<String, Object> row) {
        String sql = "insert into OrderClient values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
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

    //删除一个客户
    // done

    public boolean removeOrderClient(int orderID) {
        String sql = "delete from OrderClient where orderID = " + orderID;

        boolean flag = false;
        try {
            flag = jdbcUtils.updateByPreparedStatement(sql, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    // done
    // 保存一个订单的客户

    public boolean saveOrderClient(int orderID, int clientID) {
        String sql = "";
        sql = "update OrderClient set clientID = " + clientID + ", clientName = ? where orderID = " + orderID;

        ClientUtils clientUtils = new ClientUtils();
        String searchName = clientUtils.searchClientByID(clientID).get("name").toString();
        List<Object> params = new ArrayList<Object>();
        params.add(searchName);

        boolean flag = false;
        try {
            flag = jdbcUtils.updateByPreparedStatement(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    // done
    // 改变订单状态

    public boolean changeBoolean(int orderID,String column) {
        String sql = "";
        sql = "update OrderClient set " + column + " = 1 - " + column + " where orderID = " + orderID;

        boolean flag = false;
        try {
            flag = jdbcUtils.updateByPreparedStatement(sql, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    // 向表中填入现有时间
    // done

    public boolean changeDateByNow(int orderID,String column) {
        Date date = new Date();
        Timestamp timeStamp = new Timestamp(date.getTime());
        String sql = "";
        sql = "update OrderClient set " + column + " = ? where orderID = " + orderID;

        List<Object> params = new ArrayList<Object>();
        params.add(timeStamp);
        boolean flag = false;
        try {
            flag = jdbcUtils.updateByPreparedStatement(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    //更新订单总价

    public boolean updateMoney(int orderID) {

        String sql = "";

        sql = "select sum(count*price) from OrderCommodity where orderID = " + orderID + " group by orderID";
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map = jdbcUtils.findSimpleResult(sql, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (map.get("sum(count*price)")==null) return true;
        sql = "update OrderClient set money = ? where orderID = " + orderID;
        List<Object> params = new ArrayList<Object>();
        params.add(map.get("sum(count*price)"));
        boolean flag = false;
        try {
            flag = jdbcUtils.updateByPreparedStatement(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean decOrderID(int id) {
        String sql = "";
        sql = "update OrderClient set orderID = ? where orderID = ?";

        List<Object> params = new ArrayList<Object>();
        params.add(Integer.toString(id-1));
        params.add(Integer.toString(id));

        //     System.out.println(map.get("address"));
        boolean flag = false;
        try {
            flag = jdbcUtils.updateByPreparedStatement(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

}
