package connect4fx;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class ConfirmBox {

    static boolean answer;
    
    public static boolean display(String title, String message){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        Label label = new Label();
        label.setText(message);
        
        Button botonSi = new Button("Si");
        Button botonNo = new Button("No");
        
        botonSi.setOnAction(e -> {
            answer = true;
            window.close();
        });
        
        botonNo.setOnAction(e -> {
            answer = false;
            window.close();
        });
        
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, botonSi, botonNo);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        
        return answer;
    }
}
