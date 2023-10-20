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
import java.util.ArrayList;
import java.util.List;

public class PatientDischargePageController {
    public Label discharge_section_label;
    public TextField patient_national_code_text_field;
    public Button accept_button;
    public Button cancel_button;

    public int getData () {
        Doctor doctor = new Doctor ();
        List<String> data = new ArrayList<>();
        data.add (patient_national_code_text_field.getText());
        int result1 = doctor.writeSurgeryData ("src/Project1/myFile/Discharge.txt", data);
        return result1;
    }

    public String[] arrayOfString () {
        String[] text = new String[1];
        text[0] = patient_national_code_text_field.getText();
        return text;
    }

    public void onClickAcceptButton () {
        Doctor doctor = new Doctor ();
        FilesAndTools myTool = new FilesAndTools ();
        int result6 = myTool.emptyTextFieldCheck (arrayOfString ());
        if (result6 == 0) { // if text field empty
            int result4 = doctor.createFile("src/Project1/myFile/Discharge.txt");
            if (result4 == 0) {
                doctor.writeFirstDischarge("src/Project1/myFile/Discharge.txt");
            }
            int result1 = doctor.checkPatientNationalCode("src/Project1/myFile/GroupTherapy.txt", "src/Project1/myFile/LogInDoctor.txt", patient_national_code_text_field.getText());
            int result2 = doctor.checkNationalCodeForDischarge("src/Project1/myFile/Discharge.txt", patient_national_code_text_field.getText());
            if (result1 == 1 && result2 == 0) { // check if national code exist
                int result3 = getData();
                if (result3 == 0) { // check if write to file success
                    myTool.audioPlayer("src/Project1/myAudio/access.wav");
                    myTool.createNotificationPage("Report Registered !", "/project1/myPicture/error.png", "Access", 500, 150);
                } else { // else write to file errors
                    myTool.audioPlayer("src/Project1/myAudio/notification.wav");
                    myTool.createNotificationPage("Writing to File Errors", "/project1/myPicture/error.png", "Error", 600, 150);
                }
            } else { // check of reading writing creating file errors
                myTool.audioPlayer("src/Project1/myAudio/notification.wav");
                myTool.createNotificationPage(doctor.backStringError4(result1, result2), "/project1/myPicture/error.png", "Error", 600, 150);
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
        Stage primaryStage = (Stage) discharge_section_label.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("myFXMLFile/doctor_section_page.fxml"));
        Scene myScene = new Scene (root);
        myScene.setFill (Color.TRANSPARENT);
        primaryStage.setScene (myScene);
        primaryStage.show();
    }
}
