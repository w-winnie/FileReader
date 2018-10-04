package content;

public enum Products {
    HARRYPOTTER("Harry Potter", 32.97),
    PERCYJACKSON("Percy Jackson", 30.76),
    MORTALINSTRUMENTS("Mortal Instruments", 15.99),
    HUNGERGAMES("Hunger games", 17.00),
    MAZERUNNER("Maze Runner", 10.54),
    OTHERS("Others", 0.0);

    private String name = "";
    private double price = 0;

    Products(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    //Method to verify the product
    public static Products checkProduct(String productStr) {

        for (Products sub : Products.values()) {
            if (productStr.trim().equalsIgnoreCase(sub.getName())) {
                return sub;
            }
        }
        return Products.OTHERS;
    }
}
