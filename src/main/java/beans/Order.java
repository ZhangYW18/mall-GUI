package beans;

import java.util.Date;
import java.util.Map;
import java.util.List;

public class Order {
    private int id;
    private int clientID;
    private List<Map<Integer, Integer>> commodity;
    private Date startTime;
    private Date sentTime;
    private boolean isSent;
    private boolean hasReceipt;

    public Order (){
        isSent=false;
        hasReceipt = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSent() {
        return isSent;
    }

    public void setSent() {
        isSent = true;
    }

    public Date getSentTime() {
        return sentTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setSentTime() {
        this.sentTime = new Date();
    }

    public void setStartTime() {
        this.startTime = new Date();
    }

    public int getClientID() {
        return clientID;
    }

    public List<Map<Integer, Integer>> getCommodity() {
        return commodity;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public void setCommodity(List<Map<Integer, Integer>> commodity) {
        this.commodity = commodity;
    }

    public void setHasReceipt() {
        this.hasReceipt = true;
    }
}
