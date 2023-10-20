package Project1;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {
    public Button accounting_section_button;
    public Button doctor_section_button;
    public Button nurse_section_button;
    public Button patient_section_button;
    public Label main_label;
    public Button clear_all_history_button;

    public int deleteAllFile () {
        FilesAndTools myTool = new FilesAndTools ();
        int result = 0;
        result += myTool.ifExistThenDelete ("src/Project1/myFile/Bill.txt");
        result += myTool.ifExistThenDelete ("src/Project1/myFile/Diagnosis.txt");
        result += myTool.ifExistThenDelete ("src/Project1/myFile/Discharge.txt");
        result += myTool.ifExistThenDelete ("src/Project1/myFile/GroupTherapy.txt");
        result += myTool.ifExistThenDelete ("src/Project1/myFile/LogInDoctor.txt");
        result += myTool.ifExistThenDelete ("src/Project1/myFile/LogInNurse.txt");
        result += myTool.ifExistThenDelete ("src/Project1/myFile/LogInPatient.txt");
        result += myTool.ifExistThenDelete ("src/Project1/myFile/SignInDoctor.txt");
        result += myTool.ifExistThenDelete ("src/Project1/myFile/SignInNurse.txt");
        result += myTool.ifExistThenDelete ("src/Project1/myFile/SignInPatient.txt");
        result += myTool.ifExistThenDelete ("src/Project1/myFile/Surgery.txt");
        result += myTool.ifExistThenDelete("src/Project1/myFile/Visit.txt");
        return result;
    }

    public void onClickAccountingSection () throws IOException {
        FilesAndTools myTool = new FilesAndTools ();
        myTool.audioPlayer ("src/Project1/myAudio/click.wav");
        Stage primaryStage = (Stage) main_label.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("myFXMLFile/accounting_page.fxml"));
        Scene myScene = new Scene (root);
        myScene.setFill (Color.TRANSPARENT);
        primaryStage.setScene (myScene);
        primaryStage.show();
    }

    public void onClickDoctorSection () throws IOException {
        FilesAndTools myTool = new FilesAndTools ();
        myTool.audioPlayer ("src/Project1/myAudio/click.wav");
        Stage primaryStage = (Stage) main_label.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("myFXMLFile/login_doctor_page.fxml"));
        Scene myScene = new Scene (root);
        myScene.setFill (Color.TRANSPARENT);
        primaryStage.setScene (myScene);
        primaryStage.show();
    }

    public void onClickNurseSection () throws IOException {
        FilesAndTools myTool = new FilesAndTools ();
        myTool.audioPlayer ("src/Project1/myAudio/click.wav");
        Stage primaryStage = (Stage) main_label.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("myFXMLFile/login_nurse_page.fxml"));
        Scene myScene = new Scene (root);
        myScene.setFill (Color.TRANSPARENT);
        primaryStage.setScene (myScene);
        primaryStage.show();
    }

    public void onClickPatientSection () throws IOException {
        FilesAndTools myTool = new FilesAndTools ();
        myTool.audioPlayer ("src/Project1/myAudio/click.wav");
        Stage primaryStage = (Stage) main_label.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("myFXMLFile/log_in_patient_page.fxml"));
        Scene myScene = new Scene (root);
        myScene.setFill (Color.TRANSPARENT);
        primaryStage.setScene (myScene);
        primaryStage.show();
    }

    public void onClickClearAllHistoryButton () {
        FilesAndTools myTool = new FilesAndTools ();
        if (deleteAllFile () == 0) {
            myTool.audioPlayer ("src/Project1/myAudio/access.wav");
            myTool.createNotificationPage("All data cleared !", "/project1/myPicture/check.png", "Files Deleted !", 300, 100);
        }
        else {
            myTool.audioPlayer ("src/Project1/myAudio/notification.wav");
            myTool.createNotificationPage("Clearing Data Errors !", "/project1/myPicture/warning.png", "Error", 500, 150);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
