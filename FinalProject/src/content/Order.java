package content;

//Order class, to build an order object
public class Order {

    //String variables for order ID, name, address, city and product name
    private String orderID, name, address, city, product;
    //Double variable for price
    private double price;
    //Integer variable for quantity
    private int quantity;

    //Order constructor with OrderID
    public Order(String orderID) {
        this.orderID = orderID;
    }

    //To get the orderId of the object
    public String getOrderID() {
        return this.orderID;
    }

    //To get the name of the object
    public String getName() {
        return this.name;
    }

    //To get the address of the object
    public String getAddress() {
        return this.address;
    }

    //To get the city of the object
    public String getCity() {
        return this.city;
    }

    //To get the product of the object
    public String getProduct() {
        return this.product;
    }

    //To get the price of the object
    public double getPrice() {
        return this.price;
    }

    //To get the quantity of the object
    public int getQuantity() {
        return this.quantity;
    }

    //To set the name of the object
    public void setName(String name) {
        this.name = name;
    }

    //To set the address of the object
    public void setAddress(String address) {
        this.address = address;
    }

    //To set the city of the object
    public void setCity(String city) {
        this.city = city;
    }

    //To set the product of the object
    public void setProduct(String product) {
        this.product = product;
    }

    //To set the price of the object
    public void setPrice(double price) {
        this.price = price;
    }

    //To set the quantity of the object
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
