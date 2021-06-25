package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EndGamePageController implements Initializable {

    @FXML
    private Label winnerLBL;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setText();
    }


    private void setText(){
        if (GamePageController.user1.getPoint() > GamePageController.user2.getPoint())
            winnerLBL.setText("--- " + GamePageController.user1.getUserName() + " Won ---");
        else if (GamePageController.user1.getPoint() < GamePageController.user2.getPoint())
            winnerLBL.setText("--- " + GamePageController.user2.getUserName() + " Won ---");
        else
            winnerLBL.setText("--- Oops !!! NT , Draw ---");

        Thread thread = new Thread(() ->{

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Stage loginStage = ((Stage) winnerLBL.getScene().getWindow());

                    FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/LoginPage.fxml"));

                    try {
                        loader.load();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    Image image = new Image("/images/imgbin_circle-png.png");
                    loginStage.getIcons().add(image);
                    loginStage.setScene(new Scene(loader.getRoot()));
                    loginStage.setTitle("Othello ~Designed by : SaraNikMehr & MohammadHMazarei~");
                    loginStage.resizableProperty().setValue(Boolean.FALSE);
                    loginStage.show();
                }
            });
        });
        thread.start();
    }
}
