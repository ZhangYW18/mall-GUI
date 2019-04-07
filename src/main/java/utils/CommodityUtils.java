/*
 * @author VampireWeekend
 * @date 19-4-6 下午6:41
 */

package utils;


import javax.sound.midi.Soundbank;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utils.*;


public class CommodityUtils {
    private JdbcUtils jdbcUtils;
    private String[] tableStrings = {"id", "name", "description", "brand", "price", "count", "sold"};

    public CommodityUtils() {
        jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
    }


    //获得所有客户信息

    public List<Map<String, Object>> findAllCommodity() {
        String sql = "select * from Commodity";

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            list = jdbcUtils.findModeResult(sql, null);
            // System.out.println(list);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //查找客户信息

    public List<Map<String, Object>> searchCommodity(Map<String, Object> row) {
        String sql = "select * from Commodity where ";
        List<Object> params = new ArrayList<Object>();


        //      sql = "select * from Commodity where TRUE AND sex=1 AND name LIKE \'%so%\' AND address LIKE \'%%\'";
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

    //根据ID查找客户信息
    public Map<String, Object> searchCommodityByID(String id) {
        String sql = "select * from Commodity where id LIKE \'" + id + "\'";

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

    public boolean addCommodity(Map<String, Object> row) {
        String sql = "insert into Commodity values (?, ?, ?, ?, ?, ?, ?)";
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

    //删除一个客户

    public boolean removeCommodity(String id) {
        String sql = "delete from Commodity where id = ?";
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


    // 保存一个客户

    public boolean saveCommodity(Map<String, Object> map) {
        String sql = "";
        if (map.containsKey("id")) {
            sql = "update Commodity set name = ? , description = ?, brand = ?, price = ?, count = ? where id = ?";
        }

        List<Object> params = new ArrayList<Object>();
        params.add(map.get("name"));
        params.add(map.get("description"));
        params.add(map.get("brand"));
        params.add(map.get("price"));
        params.add(map.get("count"));
        params.add(map.get("id"));

        //     System.out.println(map.get("address"));
        boolean flag = false;
        try {
            flag = jdbcUtils.updateByPreparedStatement(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }



    public boolean addCommodityCountByID(String ID,int count) {
        String sql = "";
        sql = "update Commodity set count = count + " +count + " where id = \'" + ID + "\'";

        boolean flag = false;
        try {
            flag = jdbcUtils.updateByPreparedStatement(sql, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
}

