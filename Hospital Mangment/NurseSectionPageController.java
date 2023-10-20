package Project1;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NurseSectionPageController {
    public Label visit_patient_label;
    public TextField patient_national_code_text_field;
    public TextField patient_status_text_field;
    public TextField cost_of_visit_text_field;
    public TextField time_of_visit;
    public TextArea description_of_visit;
    public Button accept_button;
    public Button cancel_button;

    public int getData () {
        Nurse nurse = new Nurse ();
        List<String> data = new ArrayList<>();
        data.add (patient_national_code_text_field.getText());
        data.add (patient_status_text_field.getText());
        data.add (cost_of_visit_text_field.getText());
        data.add (time_of_visit.getText());
        data.add (description_of_visit.getText());
        int result1 = nurse.writeVisitData ("src/Project1/myFile/Visit.txt", data);
        return result1;
    }

    public String[] arrayOfString () {
        String[] text = new String[5];
        text[0] = patient_national_code_text_field.getText();
        text[1] = patient_status_text_field.getText();
        text[2] = cost_of_visit_text_field.getText();
        text[3] = time_of_visit.getText();
        text[4] = description_of_visit.getText();
        return text;
    }

    public void onClickAcceptButton () {
        Nurse nurse = new Nurse ();
        FilesAndTools myTool = new FilesAndTools ();
        int result6 = myTool.emptyTextFieldCheck (arrayOfString ());
        if (result6 == 0) { // if text field empty
            int result4 = nurse.createFile("src/Project1/myFile/Visit.txt");
            int result1 = nurse.checkPatientNationalCode("src/Project1/myFile/GroupTherapy.txt"
                    , "src/Project1/myFile/LogInNurse.txt", patient_national_code_text_field.getText());
            if (result1 == 1) { // check if national code exist
                int result2 = getData();
                if (result2 == 0) { // check if write to file success
                    myTool.audioPlayer("src/Project1/myAudio/access.wav");
                    myTool.createNotificationPage("Report Registered !", "/project1/myPicture/error.png", "Access", 500, 150);
                } else { // else write to file errors
                    myTool.audioPlayer("src/Project1/myAudio/notification.wav");
                    myTool.createNotificationPage("Writing to File Errors", "/project1/myPicture/error.png", "Error", 600, 150);
                }
            } else { // check of reading writing creating file errors
                myTool.audioPlayer("src/Project1/myAudio/notification.wav");
                myTool.createNotificationPage(nurse.backStringError3(result1), "/project1/myPicture/error.png", "Error", 600, 150);
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
        Stage primaryStage = (Stage) visit_patient_label.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("myFXMLFile/main_page.fxml"));
        Scene myScene = new Scene (root);
        myScene.setFill (Color.TRANSPARENT);
        primaryStage.setScene (myScene);
        primaryStage.show();
    }
}
