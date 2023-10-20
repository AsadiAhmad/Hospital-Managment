package Project1;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;

public class DoctorSectionPageController {
    public Label doctor_section_page_label;
    public Button diagnosis_button;
    public Button surgery_button;
    public Button patient_discharge_button;
    public Button exit_button;

    public void onClickDiagnosisButton () throws IOException {
        FilesAndTools myTool = new FilesAndTools ();
        myTool.audioPlayer ("src/Project1/myAudio/click.wav");
        Stage primaryStage = (Stage) doctor_section_page_label.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("myFXMLFile/diagnosis_page.fxml"));
        Scene myScene = new Scene (root);
        myScene.setFill (Color.TRANSPARENT);
        primaryStage.setScene (myScene);
        primaryStage.show();
    }

    public void onClickSurgeryButton () throws IOException {
        FilesAndTools myTool = new FilesAndTools ();
        myTool.audioPlayer ("src/Project1/myAudio/click.wav");
        Stage primaryStage = (Stage) doctor_section_page_label.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("myFXMLFile/surgery_page.fxml"));
        Scene myScene = new Scene (root);
        myScene.setFill (Color.TRANSPARENT);
        primaryStage.setScene (myScene);
        primaryStage.show();
    }

    public void onClickPatientDischargeButton () throws IOException {
        FilesAndTools myTool = new FilesAndTools ();
        myTool.audioPlayer ("src/Project1/myAudio/click.wav");
        Stage primaryStage = (Stage) doctor_section_page_label.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("myFXMLFile/patient_discharge_page.fxml"));
        Scene myScene = new Scene (root);
        myScene.setFill (Color.TRANSPARENT);
        primaryStage.setScene (myScene);
        primaryStage.show();
    }

    public void onClickExitButton () throws IOException {
        FilesAndTools myTool = new FilesAndTools ();
        myTool.audioPlayer ("src/Project1/myAudio/click.wav");
        Stage primaryStage = (Stage) doctor_section_page_label.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("myFXMLFile/main_page.fxml"));
        Scene myScene = new Scene (root);
        myScene.setFill (Color.TRANSPARENT);
        primaryStage.setScene (myScene);
        primaryStage.show();
    }
}
