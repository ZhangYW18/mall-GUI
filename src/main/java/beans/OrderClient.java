/*
 * @author VampireWeekend
 * @date 19-4-7 下午10:21
 */

package beans;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderClient {
    private int orderID;
    private int clientID;
    private Date startTime;
  //  private double money;     //should be gotten through sum() in sql
    private boolean isPaid;
    private boolean isSent;
    private Date sentTime;
    private boolean hasReceipt;

    public OrderClient() {}
}
