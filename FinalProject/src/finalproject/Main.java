package finalproject;

import UserExceptions.DataIncompleteException;
import UserExceptions.DataOutOfBoundsException;
import UserExceptions.ProductAbsentException;
import content.Order;
import content.Products;
import content.TheFile;
import content.TheList;
import contentFX.StageDisplay;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    //Labels for the main screen
    private final Label lblOrderID = new Label("OrderID: ");
    private final Label lblName = new Label("Name: ");
    private final Label lblAddress = new Label("Address: ");
    private final Label lblCity = new Label("City: ");
    private final Label lblProduct = new Label("Product: ");
    private final Label lblQuantity = new Label("Quantity: ");
    private final Label lblPrice = new Label("Price: ");
    private final Label lblSearch = new Label("SEARCH: ");

    //Textfields
    private final TextField txtName = new TextField();
    private final TextField txtAddress = new TextField();
    private final TextField txtCity = new TextField();
    private final TextField txtProduct = new TextField();
    private final TextField txtQuantity = new TextField();
    private final TextField txtSearch = new TextField();

    //Top bar Buttons
    private final Button btnPrev = new Button("Previous");
    private final Button btnNext = new Button("Next");
    private final Button btnFirst = new Button("First");
    private final Button btnLast = new Button("Last");

    //Bottom bar buttons
    private final Button btnInsert = new Button("Insert");
    private final Button btnModify = new Button("Modify");
    private final Button btnDelete = new Button("Delete");
    private final Button btnSearch = new Button("Search");
    private final Button btnClear = new Button("Clear");
    private final Button btnSave = new Button("Save");
    private final Button btnDisplay = new Button("Display");

    //Drop sown box for search
    private final ComboBox comboSearch = new ComboBox();

    //Alert or pop up boxes
    private final Alert dlgConfirm = new Alert(AlertType.CONFIRMATION);
    private final Alert dlgError = new Alert(AlertType.ERROR);

    //Pane object
    private final Pane pane = new Pane(createPane());

    //Scene object 
    private final Scene scene = new Scene(pane, 550, 500);

    //File object referencing the OrderFile 
    private static File file = new File("OrderFile");

    //Variable count to keep track of index throughout the program
    private int count = 0;

    //String to get the category to search from the combo box
    private String txtSearchCategory = new String();

    @Override
    public void start(Stage primaryStage) {

        //Method to show data in the text fields on the screen
        showDataFields(count);

        //To import stylesheet
        scene.getStylesheets().add("/style/TheStyle.css");

        //Event handler for insert button
        btnInsert.setOnAction((e) -> {
            //Alert box to confirm insert
            alertConfirm("Insert");
            //If the insert action is confirmed
            if (resultConfirm()) {
                try {
                    //Method to insert data into ArrayList
                    insertData();
                } catch (DataIncompleteException | ProductAbsentException ex) {
                    //Displaying error alert box with the exception message
                    alertError(ex.getMessage()).show();
                } finally {
                    //Method to show the display stage with the data
                    showStageDisplay(selectedDataStr(), TheList.display());
                }
            }

        });

        //Event handler for Modify button
        btnModify.setOnAction((e) -> {
            //Alert box to confirm modify
            alertConfirm("Modify");
            //If the modify action is confirmed
            if (resultConfirm()) {
                try {
                    //Method to modify data into ArrayList
                    modifyData(count);
                } catch (DataIncompleteException | ProductAbsentException ex) {
                    //Displaying error alert box with the exception message
                    alertError(ex.getMessage()).show();
                } finally {
                    //Method to show the display stage with the data
                    showStageDisplay(selectedDataStr(), TheList.display());
                }
            }
        });

        //Event handler for Delete button
        btnDelete.setOnAction((e) -> {
            //Alert box to confirm delete
            alertConfirm("Delete");
            //If the delete action is confirmed
            if (resultConfirm()) {
                //Method to delete data from ArrayList
                deleteData(count);
            }
            //Method to show the display stage with the data
            showStageDisplay(selectedDataStr(), TheList.display());
        });

        //Event handler for Search button
        btnSearch.setOnAction((e) -> {
            //Method to show the display stage with the data
            showStageDisplay(txtSearch.getText(), searchData());
        });

        //Event handler for Save button
        btnSave.setOnAction((e) -> {
            //Alert box to confirm save
            alertConfirm("Save");
            //If the save action is confirmed
            if (resultConfirm()) {
                try {
                    //Creating a file object for the OrderFile
                    File file = new File("OrderFile");
                    //Creating a new file
                    file.createNewFile();
                    //Method to write the data from the ArrayList into the file
                    TheFile.writeFile(file, TheList.toFileStr());
                } catch (IOException ex) {
                    //Displaying alert error with the exception message
                    alertError(ex.getMessage());
                }
            }
        });

        //Event handler for close
        primaryStage.setOnCloseRequest((e) -> {
            //Alert box to confirm save
            alertConfirm("Save");
            if (resultConfirm()) {
                try {
                    //Creating a file object for the OrderFile
                    File file = new File("OrderFile");
                    //Creating a new file
                    file.createNewFile();
                    //Method to write the data from the ArrayList into the file
                    TheFile.writeFile(file, TheList.toFileStr());
                } catch (IOException ex) {
                    //displaying alert error with the exception message
                    alertError(ex.getMessage());
                }
            }
        });

        //Event handler for Clear button
        btnClear.setOnAction((e) -> {
            //Method to clear the text fields
            clearData();
        });

        //Event handler for Display button
        btnDisplay.setOnAction((e) -> {
            //Method to display all the data in StageDisplay
            showStageDisplay("The Following contains list of all the data", TheList.display());
        });

        //Event handler for the button First
        btnFirst.setOnAction((e) -> {
            //Method to display the first Order object into the text fields
            showDataFields(0);
            //set the index to 0
            count = 0;
        });

        //Event handler for the button Last
        btnLast.setOnAction((e) -> {
            //Method to display the last Order object into the text fields
            showDataFields(TheList.getSize() - 1);
            //set the index to that of the last objectin the arraylist
            count = TheList.getSize() - 1;
        });

        //Event handler for the button Next
        btnNext.setOnAction((e) -> {
            try {
                if (count < TheList.getSize() - 1 && count >= 0) {
                    count++;
                    //Method to display Order object in the text fields
                    showDataFields(count);
                } else {
                    throw new DataOutOfBoundsException();
                }
            } catch (DataOutOfBoundsException ex) {
                //Displaying alert error with exception message
                alertError(ex.getMessage()).show();
            }
        });

        //Event handler for the button Previous
        btnPrev.setOnAction((e) -> {
            try {
                if (count <= TheList.getSize() && count > 0) {
                    count--;
                    //Method to display Order object in the text fields
                    showDataFields(count);
                } else {
                    throw new DataOutOfBoundsException();
                }
            } catch (DataOutOfBoundsException ex) {
                //Displaying alert error with exception message
                alertError(ex.getMessage()).show();
            }
        });

        primaryStage.setTitle("OrderManagement");
        primaryStage.setX(150);
        primaryStage.setY(100);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    //Method to set the objects into the grid pane
    private GridPane createPane() {
        GridPane gpane = new GridPane();

        //Adding options to the ComboBox for search
        comboSearch.getItems().add("OrderID");
        comboSearch.getItems().add("Name");
        comboSearch.getItems().add("Address");
        comboSearch.getItems().add("City");
        comboSearch.getItems().add("Product");
        comboSearch.getItems().add("Price");
        comboSearch.getItems().add("Quantity");

        gpane.setPadding(new Insets(20));
        gpane.setHgap(10);
        gpane.setVgap(20);

        gpane.addRow(1, btnFirst, btnPrev, btnClear, btnNext, btnLast);
        gpane.addRow(2, lblOrderID);
        gpane.addRow(3, lblName, txtName);
        gpane.addRow(4, lblAddress, txtAddress);
        gpane.addRow(5, lblCity, txtCity);
        gpane.addRow(6, lblProduct, txtProduct);
        gpane.addRow(7, lblQuantity, txtQuantity);
        gpane.addRow(8, lblPrice);
        gpane.addRow(9, btnInsert, btnModify, btnDelete, btnDisplay, btnSave);
        gpane.addRow(10, lblSearch, comboSearch, txtSearch, btnSearch);

        return gpane;
    }

    //Method to customize the error alert
    private Alert alertError(String errorMsg) {
        dlgError.setTitle("ERROR!");
        dlgError.setHeaderText(errorMsg);
        dlgError.setContentText("Sorry you can't do this");
        return dlgError;
    }

    //Method to customize the alert confirm
    private Alert alertConfirm(String confirmMsg) {
        dlgConfirm.setTitle("Confirm");
        dlgConfirm.setHeaderText(confirmMsg);
        dlgConfirm.setContentText("Are you sure?");
        return dlgConfirm;
    }

    //Method to get the results from confirm alert
    private boolean resultConfirm() {
        Optional<ButtonType> result = dlgConfirm.showAndWait();
        return result.get() == ButtonType.OK;
    }

    //Method to show data of the order object into the data fields
    private void showDataFields(int num) {
        lblOrderID.setText(TheList.getObject(num).getOrderID());
        txtName.setText(TheList.getObject(num).getName());
        txtAddress.setText(TheList.getObject(num).getAddress());
        txtCity.setText(TheList.getObject(num).getCity());
        txtProduct.setText(TheList.getObject(num).getProduct());
        txtQuantity.setText(Integer.toString(TheList.getObject(num).getQuantity()));
        lblPrice.setText("$" + TheList.getObject(num).getPrice());
    }

    //Method to insert data into the arraylist as an Orde object
    private void insertData() throws DataIncompleteException, ProductAbsentException {
        if (txtName.getText().isEmpty() || txtAddress.getText().isEmpty()
                || txtCity.getText().isEmpty() || txtProduct.getText().isEmpty()
                || txtQuantity.getText().isEmpty()) {
            throw new DataIncompleteException();
        }
        //Building an Order object
        Order orderObj = new Order(TheList.getNewOrderID('O'));
        orderObj.setName(txtName.getText());
        orderObj.setAddress(txtAddress.getText());
        orderObj.setCity(txtCity.getText());
        Products product = Products.checkProduct(txtProduct.getText());
        if (product == Products.OTHERS) {
            throw new ProductAbsentException();
        } else {
            orderObj.setProduct(product.getName());
        }
        orderObj.setQuantity(Integer.parseInt(txtQuantity.getText()));
        orderObj.setPrice(product.getPrice());
        //Adding the order object to list
        TheList.addToList(orderObj);
    }

    //Method to modify data (Order object) in the arraylist
    private void modifyData(int index) throws DataIncompleteException, ProductAbsentException {
        if (txtName.getText().isEmpty() || txtAddress.getText().isEmpty()
                || txtCity.getText().isEmpty() || txtProduct.getText().isEmpty()
                || txtQuantity.getText().isEmpty()) {
            throw new DataIncompleteException();
        }
        //Building an order object
        Order orderObj = new Order(lblOrderID.getText());
        orderObj.setName(txtName.getText());
        orderObj.setAddress(txtAddress.getText());
        orderObj.setCity(txtCity.getText());
        Products product = Products.checkProduct(txtProduct.getText());
        if (product == Products.OTHERS) {
            throw new ProductAbsentException();
        } else {
            orderObj.setProduct(product.getName());
        }
        orderObj.setQuantity(Integer.parseInt(txtQuantity.getText()));
        orderObj.setPrice(product.getPrice());
        //Modifying the object in the list
        TheList.modifyList(index, orderObj);
    }

    //Method to delete data (Order object) from the arraylist
    private void deleteData(int index) {
        TheList.deleteFromList(index);
    }

    //Method to search through data fields in the arraylist
    private String searchData() {
        String result = new String();
        //Getting the data field
        txtSearchCategory = (String) comboSearch.getValue();
        //Searching through the specific data field for the string entered
        result = TheList.searchList(txtSearchCategory, txtSearch.getText());
        return result;
    }

    //Method to clear the text fields
    private void clearData() {
        txtName.clear();
        txtAddress.clear();
        txtCity.clear();
        txtProduct.clear();
        txtQuantity.clear();
        txtSearch.clear();
        lblOrderID.setText("");
        lblPrice.setText("");
        txtName.requestFocus();
    }

    //Method to save the data to the file
    private void saveToFile() throws IOException {
        TheFile.writeFile(file, TheList.toFileStr());
    }

    //Method to build a string with the current data in the textfields
    private String selectedDataStr() {
        String str = " Name: " + txtName.getText()
                + "\n Address: " + txtAddress.getText()
                + "\n City: " + txtCity.getText()
                + "\n Product: " + txtProduct.getText()
                + "\n Quantity: " + txtQuantity.getText();
        return str;
    }

    //Method to show the secondary stage, StageDisplay
    private void showStageDisplay(String dataString, String list) {
        StageDisplay stage = new StageDisplay(dataString, list);
        stage.show();
    }

    public static void main(String[] args) {
        //Method to load the file data into the arraylist
        try {
            if (file.exists()) {
                TheList.loadList();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        launch(args);
    }

}
