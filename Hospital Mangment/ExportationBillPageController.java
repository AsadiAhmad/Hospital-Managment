package Project1;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExportationBillPageController {
    public Label exportation_bill_label;
    public ListView my_list;
    public Button pay_bill_button;
    public Button cancel_button;

    public int getData () {
        Patient patient = new Patient ();
        List<String> data = new ArrayList<>();
        data.add (patient.gettingLastUser ("src/Project1/myFile/LogInPatient.txt"));
        int result1 = patient.writeBillData ("src/Project1/myFile/Bill.txt", data);
        return result1;
    }

    public void setList (List<String> myList) {
        Patient patient = new Patient ();
        for (int i = 0; i < patient.counterOfLines2("src/Project1/myFile/Diagnosis.txt", "src/Project1/myFile/LogInPatient.txt")
                + patient.counterOfLines2("src/Project1/myFile/Surgery.txt", "src/Project1/myFile/LogInPatient.txt")
                + patient.counterOfLines2("src/Project1/myFile/Visit.txt", "src/Project1/myFile/LogInPatient.txt") + 1; i++) {
            my_list.getItems().add(myList.get(i));
        }
    }

    public void onClickPayBillButton () {
        Patient patient = new Patient ();
        Doctor doctor = new Doctor ();
        FilesAndTools myTool = new FilesAndTools ();
        int result5 = patient.createFile ("src/Project1/myFile/Bill.txt");
        if (result5 == 0) {
            patient.writeFirstDischarge ("src/Project1/myFile/Bill.txt");
        }
        int result2 = patient.checkNationalCodeForPayBill ("src/Project1/myFile/Bill.txt", patient.gettingLastUser ("src/Project1/myFile/LogInPatient.txt"));
        if (result2 == 0) { // check if national code exist
            int result3 = doctor.checkNationalCodeForDischarge ("src/Project1/myFile/Discharge.txt"
                    , patient.gettingLastUser ("src/Project1/myFile/LogInPatient.txt"));
            if (result3 == 1) { // check if a patient discharged
                int result4 = getData();
                if (result4 == 0) { // check if write to file success
                    myTool.audioPlayer("src/Project1/myAudio/access.wav");
                    myTool.createNotificationPage("Bill payed !", "/project1/myPicture/error.png", "Access", 500, 150);
                } else { // else write to file errors
                    myTool.audioPlayer("src/Project1/myAudio/notification.wav");
                    myTool.createNotificationPage("Writing to File Errors", "/project1/myPicture/error.png", "Error", 600, 150);
                }
            }
            else { // else patient is not discharged
                myTool.audioPlayer("src/Project1/myAudio/notification.wav");
                myTool.createNotificationPage("Patient is Not Discharged", "/project1/myPicture/error.png", "Error", 600, 150);
            }
        }
        else { // check of reading writing creating file errors
            myTool.audioPlayer ("src/Project1/myAudio/notification.wav");
            myTool.createNotificationPage (patient.backStringError2 (result2), "/project1/myPicture/error.png", "Error", 600, 150);
        }
    }

    public void onClickCancelButton () throws IOException {
        FilesAndTools myTool = new FilesAndTools ();
        myTool.audioPlayer ("src/Project1/myAudio/click.wav");
        Stage primaryStage = (Stage) exportation_bill_label.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("myFXMLFile/patient_section_page.fxml"));
        Scene myScene = new Scene (root);
        myScene.setFill (Color.TRANSPARENT);
        primaryStage.setScene (myScene);
        primaryStage.show();
    }
}
