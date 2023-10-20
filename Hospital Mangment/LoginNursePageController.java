package Project1;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginNursePageController {
    public Label login_page_label;
    public Button sign_in_button;
    public Button log_in_button;
    public Button exit_button;

    public void onClickSignInButton () throws IOException {
        FilesAndTools myTool = new FilesAndTools ();
        myTool.audioPlayer ("src/Project1/myAudio/click.wav");
        Stage primaryStage = (Stage) login_page_label.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("myFXMLFile/sign_in_nurse_page.fxml"));
        Scene myScene = new Scene (root);
        myScene.setFill (Color.TRANSPARENT);
        primaryStage.setScene (myScene);
        primaryStage.show();
    }

    public void onClickLogInButton () throws IOException {
        FilesAndTools myTool = new FilesAndTools ();
        myTool.audioPlayer ("src/Project1/myAudio/click.wav");
        Stage primaryStage = (Stage) login_page_label.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("myFXMLFile/log_in_nurse_page.fxml"));
        Scene myScene = new Scene (root);
        myScene.setFill (Color.TRANSPARENT);
        primaryStage.setScene (myScene);
        primaryStage.show();
    }

    public void onClickExitButton () throws IOException {
        FilesAndTools myTool = new FilesAndTools ();
        myTool.audioPlayer ("src/Project1/myAudio/click.wav");
        Stage primaryStage = (Stage) login_page_label.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("myFXMLFile/main_page.fxml"));
        Scene myScene = new Scene (root);
        myScene.setFill (Color.TRANSPARENT);
        primaryStage.setScene (myScene);
        primaryStage.show();
    }
}
