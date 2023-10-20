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

public class LogInADoctorPageController {
    public Label log_in_label;
    public TextField user_name_text_field;
    public TextField password_text_field;
    public Button inter_button;
    public Button cancel_button;

    public String[] arrayOfString () {
        String[] text = new String[2];
        text[0] = user_name_text_field.getText();
        text[1] = password_text_field.getText();
        return text;
    }

    public void onClickInterButton () throws IOException {
        Doctor doctor = new Doctor ();
        FilesAndTools myTool = new FilesAndTools ();
        int result6 = myTool.emptyTextFieldCheck (arrayOfString ());
        if (result6 == 0) { // if text field empty
            int result1 = doctor.createFile("src/Project1/myFile/SignInDoctor.txt");
            if (result1 == 0) {
                doctor.writeFirstDoctor("src/Project1/myFile/SignInDoctor.txt");
            }
            doctor.setUserName(user_name_text_field.getText());
            doctor.setPassword(password_text_field.getText());
            int result2 = doctor.checkUserAndPassword("src/Project1/myFile/SignInDoctor.txt", doctor.getUserName(), doctor.getPassword());
            String notification;
            if (result1 == 2 || result2 == 0 || result2 == 2 || result2 == 3) {
                notification = doctor.backStringError2(result1, result2);
                myTool.audioPlayer("src/Project1/myAudio/notification.wav");
                myTool.createNotificationPage(notification, "/project1/myPicture/errorShield.png", "Error", 600, 150);
            } else {
                notification = "Inter Access !";
                doctor.settingLastUser("src/Project1/myFile/LogInDoctor.txt", user_name_text_field.getText());
                Stage primaryStage2 = (Stage) log_in_label.getScene().getWindow();
                Parent root2 = FXMLLoader.load(getClass().getResource("myFXMLFile/doctor_section_page.fxml"));
                Scene myScene = new Scene(root2);
                myScene.setFill(Color.TRANSPARENT);
                primaryStage2.setScene(myScene);
                primaryStage2.show();
                myTool.audioPlayer("src/Project1/myAudio/access.wav");
                myTool.createNotificationPage(notification, "/project1/myPicture/passwordCheck.png", "Access", 400, 150);
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
        Stage primaryStage = (Stage) log_in_label.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("myFXMLFile/login_doctor_page.fxml"));
        Scene myScene = new Scene (root);
        myScene.setFill (Color.TRANSPARENT);
        primaryStage.setScene (myScene);
        primaryStage.show();
    }
}
