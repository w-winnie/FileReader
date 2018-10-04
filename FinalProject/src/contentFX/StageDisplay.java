package contentFX;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//StageDisplay class, to set up the secondary stage 
public class StageDisplay extends Stage {

    private final Label lblInsert = new Label("Here is the data you entered: ");
    private final Label lblList = new Label("Here is all the data: ");
    private final TextArea txtShowInsert = new TextArea();
    private final TextArea txtShowList = new TextArea();

    private final VBox pane = new VBox(lblInsert, txtShowInsert, lblList, txtShowList);

    private final Scene scene = new Scene(pane, 600, 400);

    public StageDisplay(String txtInsert, String txtList) {
        pane.setPadding(new Insets(20));
        scene.getStylesheets().add("/style/TheStyle.css");
        txtShowInsert.setText(txtInsert);
        txtShowList.setText(txtList);
        super.setTitle("Your results");
        super.setX(600);
        super.setY(100);
        super.setScene(scene);
    }
}
