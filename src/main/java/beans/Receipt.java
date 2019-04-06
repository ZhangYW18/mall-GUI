package beans;
import beans.Order;
import java.util.Date;

public class Receipt extends Order{
    private Date startTIme;
    private String accountID;
    private String bank;
    public String taxNumber;

    public Receipt() {}

    @Override
    public Date getStartTime() {
        return super.getStartTime();
    }

    @Override
    public void setStartTime() {
        super.setStartTime();
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }
}
