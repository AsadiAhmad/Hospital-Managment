package Project1;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

public class PatientSectionPageController {
    public Label patient_section_page_label;
    public Button see_reports_button;
    public Button exportation_bill_button;
    public Button exit_button;

    public void onClickSeeReportsButton () throws IOException {
        FilesAndTools myTool = new FilesAndTools ();
        myTool.audioPlayer ("src/Project1/myAudio/click.wav");
        Stage primaryStage = (Stage) patient_section_page_label.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("myFXMLFile/see_reports_page.fxml"));
        Parent root = loader.load();
        SeeReportsPageController controller = loader.getController();
        Patient patient = new Patient ();
        ArrayList<String> finalList = patient.createListDiagnosis ("src/Project1/myFile/Diagnosis.txt", "src/Project1/myFile/LogInPatient.txt");
        finalList.addAll (patient.createListSurgery ("src/Project1/myFile/Surgery.txt", "src/Project1/myFile/LogInPatient.txt"));
        finalList.addAll (patient.createListVisit ("src/Project1/myFile/Visit.txt", "src/Project1/myFile/LogInPatient.txt"));
        controller.setList (finalList);

        Scene myScene = new Scene (root);
        myScene.setFill (Color.TRANSPARENT);
        primaryStage.setScene (myScene);
        primaryStage.show();
    }

    public void onClickExportationBillButton () throws IOException {
        FilesAndTools myTool = new FilesAndTools ();
        myTool.audioPlayer ("src/Project1/myAudio/click.wav");
        Stage primaryStage = (Stage) patient_section_page_label.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("myFXMLFile/exportation_bill_page.fxml"));
        Parent root = loader.load();
        ExportationBillPageController controller = loader.getController();
        Patient patient = new Patient ();
        ArrayList<String> finalList = patient.createListDiagnosisCost ("src/Project1/myFile/Diagnosis.txt"
                , "src/Project1/myFile/LogInPatient.txt", 1);
        finalList.addAll (patient.createListDiagnosisCost ("src/Project1/myFile/Surgery.txt", "src/Project1/myFile/LogInPatient.txt",2));
        finalList.addAll (patient.createListDiagnosisCost ("src/Project1/myFile/Visit.txt", "src/Project1/myFile/LogInPatient.txt",3));
        finalList.add ("Cost Of All : " + patient.costCountAll ("src/Project1/myFile/Diagnosis.txt", "src/Project1/myFile/Surgery.txt", "src/Project1/myFile/Visit.txt", "src/Project1/myFile/LogInPatient.txt"));
        controller.setList (finalList);

        Scene myScene = new Scene (root);
        myScene.setFill (Color.TRANSPARENT);
        primaryStage.setScene (myScene);
        primaryStage.show();
    }

    public void onClickExitButton () throws IOException {
        FilesAndTools myTool = new FilesAndTools ();
        myTool.audioPlayer ("src/Project1/myAudio/click.wav");
        Stage primaryStage = (Stage) patient_section_page_label.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("myFXMLFile/main_page.fxml"));
        Scene myScene = new Scene (root);
        myScene.setFill (Color.TRANSPARENT);
        primaryStage.setScene (myScene);
        primaryStage.show();
    }
}
