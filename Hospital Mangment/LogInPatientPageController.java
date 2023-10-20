package Project1;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;

public class LogInPatientPageController {
    public Label log_in_page_label;
    public TextField national_code_text_field;
    public TextField password_text_field;
    public Button inter_button;
    public Button cancel_button;

    public String[] arrayOfString () {
        String[] text = new String[2];
        text[0] = national_code_text_field.getText();
        text[1] = password_text_field.getText();
        return text;
    }

    public void onClickInterButton () throws IOException {
        Accounting account = new Accounting ();
        Patient patient = new Patient ();
        FilesAndTools myTool = new FilesAndTools ();
        int result6 = myTool.emptyTextFieldCheck (arrayOfString ());
        if (result6 == 0) { // if text field empty
            int result1 = patient.createFile("src/Project1/myFile/SignInPatient.txt");
            if (result1 == 0) {
                patient.writeFirstPatient("src/Project1/myFile/SignInPatient.txt");
            }
            account.writeFirstGroup("src/Project1/myFile/SignInDoctor.txt", "src/Project1/myFile/SignInNurse.txt", "src/Project1/myFile/SignInPatient.txt");
            int result4 = account.writeFirstGroupTherapy("src/Project1/myFile/GroupTherapy.txt");
            patient.setNationalCode(national_code_text_field.getText());
            patient.setPassword(password_text_field.getText());
            int result2 = patient.checkCodeAndPassword("src/Project1/myFile/SignInPatient.txt", patient.getNationalCode(), patient.getPassword());
            String notification;
            if (result1 == 2 || result2 == 0 || result2 == 2 || result2 == 3 || result4 == 1) {
                notification = patient.backStringError(result1, result2, result4);
                myTool.audioPlayer("src/Project1/myAudio/notification.wav");
                myTool.createNotificationPage(notification, "/project1/myPicture/errorShield.png", "Error", 600, 150);
            } else {
                notification = "inter access !";
                patient.settingLastUser("src/Project1/myFile/LogInPatient.txt", national_code_text_field.getText());
                Stage primaryStage2 = (Stage) log_in_page_label.getScene().getWindow();
                Parent root2 = FXMLLoader.load(getClass().getResource("myFXMLFile/patient_section_page.fxml"));
                Scene myScene = new Scene(root2);
                myScene.setFill(Color.TRANSPARENT);
                primaryStage2.setScene(myScene);
                primaryStage2.show();
                myTool.audioPlayer("src/Project1/myAudio/access.wav");
                myTool.createNotificationPage(notification, "/project1/myPicture/passwordCheck.png", "Access", 300, 150);
            }
        }
        else { // else program errors for empty text field
            myTool.audioPlayer("src/Project1/myAudio/notification.wav");
            myTool.createNotificationPage(result6 + " Text Field is empty", "/project1/myPicture/warning.png", "Error", 300, 150);
        }
    }

    public void onClickCancelButton () throws IOException {
        FilesAndTools myTool = new FilesAndTools ();
        myTool.audioPlayer ("src/Project1/myAudio/click.wav");
        Stage primaryStage = (Stage) log_in_page_label.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("myFXMLFile/main_page.fxml"));
        Scene myScene = new Scene (root);
        myScene.setFill (Color.TRANSPARENT);
        primaryStage.setScene (myScene);
        primaryStage.show();
    }
}
