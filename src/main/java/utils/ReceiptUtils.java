/*
 * @author VampireWeekend
 * @date 19-4-9 下午4:43
 */

package utils;

import javax.sound.midi.Soundbank;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utils.*;

public class ReceiptUtils {

    private JdbcUtils jdbcUtils;
    private String[] tableStrings = {"orderID", "clientID", "receiptTime", "money"};

    public ReceiptUtils() {
        jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
    }

    //获得所有发票信息

    public List<Map<String, Object>> findAllReceipt() {
        String sql = "select orderID, clientID, name, company, taxNumber, account, money, receiptTime" +
                " from Receipt, Client" +
                " where Receipt.clientID = Client.id " +
                " order by receiptTime desc";

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            list = jdbcUtils.findModeResult(sql, null);
            // System.out.println(list);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //按订单查找发票信息

    public Map<String, Object> searchReceiptByOrder(int orderID) {
        String sql = "select orderID, clientID, name, company, taxNumber, account, money, receiptTime" +
                " from Receipt, Client" +
                " where Receipt.clientID = Client.id and Receipt.orderID = " + orderID +
                " order by receiptTime desc";

        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map = jdbcUtils.findSimpleResult(sql, null);
            // System.out.println(list);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    //按客户查找发票信息

    public List<Map<String, Object>> searchReceiptByClient(int clientID) {
        String sql = "select orderID, clientID, name, company, taxNumber, account, money, receiptTime" +
                " from Receipt, Client" +
                " where Receipt.clientID = Client.id and Receipt.clientID = " + clientID +
                " order by receiptTime desc";
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            list = jdbcUtils.findModeResult(sql, null);
            // System.out.println(list);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 按商品查询发票

    public List<Map<String, Object>> searchReceiptByCommodity(String commodityID) {
        String sql = "select orderID, clientID, name, company, taxNumber, account, money, receiptTime" +
                " from Receipt, Client" +
                " where Receipt.clientID = Client.id " +
                " and Receipt.orderID in (select orderID from OrderCommodity where commodityID = ?)" +
                " order by receiptTime desc";

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

    //增加一个发票

    public boolean addReceipt(Map<String, Object> row) {
        String sql = "insert into Receipt values (?, ?, ?, ?)";
        List<Object> params = new ArrayList<Object>();

        for (int i=0;i<tableStrings.length;i++) {
            if (row.get(tableStrings[i])!=null) params.add(row.get(tableStrings[i])); else params.add("");
        }

        boolean flag = false;
        try {
            flag = jdbcUtils.updateByPreparedStatement(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    //删除一个发票

    public boolean removeReceipt(int orderID) {
        String sql = "delete from Receipt where orderID = ?";
        List<Object> params = new ArrayList<Object>();
        params.add(orderID);
        boolean flag = false;
        try {
            flag = jdbcUtils.updateByPreparedStatement(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

}
