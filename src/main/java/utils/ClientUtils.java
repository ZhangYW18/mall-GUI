/*
 * @author VampireWeekend
 * @date 19-4-6 下午6:41
 */

package utils;


import javax.sound.midi.Soundbank;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.*;


public class ClientUtils {
    private JdbcUtils jdbcUtils;
    private String[] tableStrings = {"id", "name", "sex", "address", "phone", "email", "company"};

    public ClientUtils() {
        jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
    }


    //获得所有客户信息

    public List<Map<String, Object>> findAllClient() {
        String sql = "select * from Client";

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

    public List<Map<String, Object>> searchClient(Map<String, Object> row) {
        String sql = "select * from Client where ";
        List<Object> params = new ArrayList<Object>();


  //      sql = "select * from Client where TRUE AND sex=1 AND name LIKE \'%so%\' AND address LIKE \'%%\'";
        int cnt=0;
        for (int i=0;i<7;i++) {
            if (tableStrings[i].equals("id")) continue;
            if (tableStrings[i].equals("sex")) {
                if (cnt!=0) sql = sql + " AND ";
                sql = sql + tableStrings[i] + "=" + row.get(tableStrings[i]);
                cnt++;
            } else {
                if (row.get(tableStrings[i]) == null) continue;
                if (cnt!=0) sql = sql + " AND ";
                sql = sql + tableStrings[i] + " LIKE \'%" + row.get(tableStrings[i]) + "%\'";
                cnt++;
            }
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

    //增加一个客户

    public boolean addClient(Map<String, Object> row) {
        String sql = "insert into Client values (?, ?, ?, ?, ?, ?, ?)";
        List<Object> params = new ArrayList<Object>();

        for (int i=0;i<7;i++) {
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

    public boolean removeClient(int id) {
        String sql = "delete from Client where id = ?";
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

    public boolean saveClient(Map<String, Object> map) {
        String sql = "";
        if (map.containsKey("id")) {
            sql = "update Client set name = ? , sex = ?, address = ?, phone = ?, email = ?, company = ? where id = ?";
        }

        List<Object> params = new ArrayList<Object>();
        params.add(map.get("name"));
        params.add(map.get("sex"));
        params.add(map.get("address"));
        params.add(map.get("phone"));
        params.add(map.get("email"));
        params.add(map.get("company"));
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

}

