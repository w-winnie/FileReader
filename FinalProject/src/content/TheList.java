package content;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

//TheList class, contains methods to modify the list
public class TheList {

    //Creating an order List
    private static ArrayList<Order> orderList = new ArrayList<Order>();

    //Method to get the object on a specific index from the list
    public static Order getObject(int index) {
        return orderList.get(index);
    }

    //Method to get the size of the list
    public static int getSize() {
        return orderList.size();
    }

    //Method to get the order ID for a new object
    public static String getNewOrderID(char c) {
        int num = orderList.size();
        num += 100;
        String str = "" + c + num;
        Iterator<Order> orderIterator = orderList.iterator();
        while (orderIterator.hasNext()) {
            Order orderobj = orderIterator.next();
            if (str.equals(orderobj.getOrderID())) {
                str = "" + (c++) + num;
            }
        }
        return str;
    }

    //Method to build a string with the arraylist data
    public static String toStr(ArrayList<Order> orderList) {
        String str = new String();
        Iterator<Order> orderIterator = orderList.iterator();
        while (orderIterator.hasNext()) {
            Order orderobj = orderIterator.next();
            str += " OrderID: " + orderobj.getOrderID()
                    + "\n Name: " + orderobj.getName()
                    + "\n Address: " + orderobj.getAddress()
                    + "\n City: " + orderobj.getCity()
                    + "\n Product: " + orderobj.getProduct()
                    + "\n Quantity: " + orderobj.getQuantity()
                    + "\n Price: " + orderobj.getPrice() + "\n\n";
        }
        return str;
    }

    //Method to load the list from the data in the file
    public static void loadList() throws IOException {
        TheFile.parseToList();
    }

    //Method to add the objects to the list
    public static void addToList(Order orderobj) {
        orderList.add(orderobj);
    }

    //Method to delete the objects from the list
    public static void deleteFromList(int index) {
        Order orderobj = orderList.get(index);
        orderList.remove(orderobj);
    }

    //Method to modify the objects in the list
    public static void modifyList(int index, Order newOrderobj) {
        deleteFromList(index);
        orderList.add(index, newOrderobj);
    }

    //Method to display the objects in the list
    public static String display() {
        return toStr(orderList);
    }

    //Method to build a string from the arraylist data to write into the file
    public static String toFileStr() throws IOException {
        String str = new String();
        for (int sub = 0; sub < orderList.size(); sub++) {
            if (sub == orderList.size() - 1) {
                str += orderList.get(sub).getOrderID()
                        + "," + orderList.get(sub).getName()
                        + "," + orderList.get(sub).getAddress()
                        + "," + orderList.get(sub).getCity()
                        + "," + orderList.get(sub).getProduct()
                        + "," + orderList.get(sub).getPrice()
                        + "," + orderList.get(sub).getQuantity();
            } else {
                str += orderList.get(sub).getOrderID()
                        + "," + orderList.get(sub).getName()
                        + "," + orderList.get(sub).getAddress()
                        + "," + orderList.get(sub).getCity()
                        + "," + orderList.get(sub).getProduct()
                        + "," + orderList.get(sub).getPrice()
                        + "," + orderList.get(sub).getQuantity() + "\n";
            }
        }
        return str;
    }

    //Method to search through the list
    public static String searchList(String category, String searchString) {
        ArrayList<Order> searchList = new ArrayList<Order>();
        String str = "";
        if ((category.trim()).equals("OrderID")) {
            for (int sub = 0; sub < orderList.size(); sub++) {
                if ((orderList.get(sub).getOrderID()).equalsIgnoreCase(searchString)) {
                    searchList.add(orderList.get(sub));
                }
            }
        }
        if ((category.trim()).equals("Name")) {
            for (int sub = 0; sub < orderList.size(); sub++) {
                if ((orderList.get(sub).getName()).equalsIgnoreCase(searchString)) {
                    searchList.add(orderList.get(sub));
                }
            }
        }
        if ((category.trim()).equals("Address")) {
            for (int sub = 0; sub < orderList.size(); sub++) {
                if ((orderList.get(sub).getAddress()).equalsIgnoreCase(searchString)) {
                    searchList.add(orderList.get(sub));
                }
            }
        }
        if ((category.trim()).equals("City")) {
            for (int sub = 0; sub < orderList.size(); sub++) {
                if ((orderList.get(sub).getCity()).equalsIgnoreCase(searchString)) {
                    searchList.add(orderList.get(sub));
                }
            }
        }
        if ((category.trim()).equals("Product")) {
            for (int sub = 0; sub < orderList.size(); sub++) {
                if ((orderList.get(sub).getProduct()).equalsIgnoreCase(searchString)) {
                    searchList.add(orderList.get(sub));
                }
            }
        }
        if ((category.trim()).equals("Quantity")) {
            for (int sub = 0; sub < orderList.size(); sub++) {
                if ((Integer.toString(orderList.get(sub).getQuantity())).equalsIgnoreCase(searchString)) {
                    searchList.add(orderList.get(sub));
                }
            }
        }
        if ((category.trim()).equals("Price")) {
            for (int sub = 0; sub < orderList.size(); sub++) {
                if ((Double.toString(orderList.get(sub).getPrice())).equalsIgnoreCase(searchString)) {
                    searchList.add(orderList.get(sub));
                }
            }
        }
        str = toStr(searchList);
        return str;
    }
}
