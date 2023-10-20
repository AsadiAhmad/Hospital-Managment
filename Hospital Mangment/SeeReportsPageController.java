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
import java.util.List;

public class SeeReportsPageController {
    public Label see_reports_label;
    public ListView my_list;
    public Button ok_button;

    public void setList (List<String> myList) {
        Patient patient = new Patient ();
        for (int i = 0; i < patient.counterOfLines("src/Project1/myFile/Diagnosis.txt", "src/Project1/myFile/LogInPatient.txt")
                + patient.counterOfLines("src/Project1/myFile/Surgery.txt", "src/Project1/myFile/LogInPatient.txt")
                + patient.counterOfLines("src/Project1/myFile/Visit.txt", "src/Project1/myFile/LogInPatient.txt") ; i++) {
            my_list.getItems().add(myList.get(i));
        }
    }

    public void onClickOkButton () throws IOException {
        FilesAndTools myTool = new FilesAndTools ();
        myTool.audioPlayer ("src/Project1/myAudio/click.wav");
        Stage primaryStage = (Stage) see_reports_label.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("myFXMLFile/patient_section_page.fxml"));
        Scene myScene = new Scene (root);
        myScene.setFill (Color.TRANSPARENT);
        primaryStage.setScene (myScene);
        primaryStage.show();
    }
}
