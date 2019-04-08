/*
 * @author VampireWeekend
 * @date 19-4-7 下午10:14
 */

/*
 * @author VampireWeekend
 * @date 19-4-7 下午9:30
 */

package utils;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

import utils.*;

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
        String sql = "select * from OrderClient";

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            list = jdbcUtils.findModeResult(sql, null);
            // System.out.println(list);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Bookmark 2019/4/7
    //查找客户信息

    public List<Map<String, Object>> searchOrderClient(Map<String, Object> row) {
        String sql = "select * from OrderClient where ";
        List<Object> params = new ArrayList<Object>();


        int cnt=0;
        for (int i=0;i<tableStrings.length;i++) {
            if (row.get(tableStrings[i]) == null) continue;
            if (cnt!=0) sql = sql + " AND ";
            sql = sql + tableStrings[i] + " LIKE \'%" + row.get(tableStrings[i]) + "%\'";
            cnt++;
        }

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
    //根据ID查找订单信息
    public Map<String, Object> searchOrderClientByOrderID(int orderID) {
        String sql = "select * from OrderClient where orderID = " + orderID;

        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map = jdbcUtils.findSimpleResult(sql, null);

            // System.out.println(map);

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

    public boolean removeOrderClient(String id) {
        String sql = "delete from OrderClient where id = ?";
        List<Object> params = new ArrayList<Object>();
        params.add(id);
        boolean flag = false;
        try {
            flag = jdbcUtils.updateByPreparedStatement(sql, params);
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

}

