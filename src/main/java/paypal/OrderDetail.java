package paypal;

public class OrderDetail {
    private String ordertName;

    private float total;



    public OrderDetail(String ordertName, String total) {
        this.ordertName = ordertName;

        this.total = Float.parseFloat(total);
    }

    public String getOrderName() {
        return ordertName;
    }



    public String getTotal() {
        return String.format("%.2f", total);
    }
}