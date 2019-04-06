package beans;

import java.util.Date;

public class Order {
    private int id;
    private int clientID;
    private int commodityID;
    private int count;
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

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
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

    public int getCommodityID() {
        return commodityID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public void setCommodityID(int commodityID) {
        this.commodityID = commodityID;
    }

    public void setHasReceipt() {
        this.hasReceipt = true;
    }
}
