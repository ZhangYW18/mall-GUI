package beans;

public class Commodity {
    private int id;
    private int count;
    private int sold;
    private int price;
    private String name;
    private String brand;

    public Commodity() {
        sold=0;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "(" +
                id + ", " + count + ", " + sold + ", " + price +
                ", \'" + name +
                "\', \'" + brand + "\')";
    }
}
