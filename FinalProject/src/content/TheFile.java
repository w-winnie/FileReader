package content;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

//TheFile class, containing methods to read and write to a file
public class TheFile {

    //Method to write to a file
    public static void writeFile(File file, String text) throws IOException {
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(text);
        bw.close();
        fw.close();
    }

    //Method to parse the file data to the list
    public static void parseToList() throws IOException {
        int orderNum = 0;
        FileReader fr = new FileReader("OrderFile");
        BufferedReader br = new BufferedReader(fr);
        String ln = br.readLine();
        while (ln != null) {
            TheList.addToList(parseLineOrder(ln));
            ln = br.readLine();
        }
        br.close();
    }

    //Method to parse the line data to Order object
    private static Order parseLineOrder(String line) throws IOException {
        StringTokenizer lineToken = new StringTokenizer(line, ",");
        Order orderObj = new Order(lineToken.nextToken());
        orderObj.setName(lineToken.nextToken());
        orderObj.setAddress(lineToken.nextToken());
        orderObj.setCity(lineToken.nextToken());
        String product = lineToken.nextToken();
        orderObj.setProduct(product);
        String price = lineToken.nextToken().trim();
        String quantity = lineToken.nextToken().trim();
        orderObj.setPrice(Double.parseDouble(price));
        orderObj.setQuantity(Integer.parseInt(quantity));
        return orderObj;
    }

}
