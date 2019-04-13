/*
 * @author VampireWeekend
 * @date 19-4-13 上午8:55
 */

package utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataUtils {

    private JdbcUtils jdbcUtils;

    public DataUtils() {
        jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
    }

    public List<Map<String, Object>> analyseByClient() {
        String sql = "select clientID, count(clientID), sum(money) from OrderClient group by clientID";

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            list = jdbcUtils.findModeResult(sql, null);
            // System.out.println(list);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Map<String, Object>> analyseByCommodity() {
        String sql = "select commodityID, sum(count*price), sum(count) from OrderCommodity" +
                " group by commodityID";

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            list = jdbcUtils.findModeResult(sql, null);
            // System.out.println(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
