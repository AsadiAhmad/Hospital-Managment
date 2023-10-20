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

public class SignInNursePageController {
    public TextField first_name_text_field;
    public TextField last_name_text_field;
    public TextField user_name_text_field;
    public TextField age_text_field;
    public TextField gender_text_field;
    public TextField degree_of_education_text_field;
    public TextField work_experience_text_field;
    public Label sign_in_label;
    public Button register_button;
    public Button cancel_button;

    public int getData (String password) {
        Nurse nurse = new Nurse ();
        nurse.setUserName (user_name_text_field.getText ());
        nurse.setFirstName (first_name_text_field.getText ());
        nurse.setLastName (last_name_text_field.getText ());
        nurse.setGender (gender_text_field.getText ());
        nurse.setAge (Integer.parseInt (age_text_field.getText ()));
        nurse.setDegreeOfEducation (Integer.parseInt (degree_of_education_text_field.getText ()));
        nurse.setWorkExperience (Integer.parseInt(work_experience_text_field.getText ()));
        nurse.setPassword (password);
        List<Integer> data1 = new ArrayList<>();
        List<String> data2 = new ArrayList<>();
        data1.add (nurse.getAge());
        data1.add (nurse.getDegreeOfEducation());
        data1.add (nurse.getWorkExperience());
        data2.add (nurse.getFirstName());
        data2.add (nurse.getLastName());
        data2.add (nurse.getGender());
        data2.add (nurse.getUserName());
        data2.add (nurse.getPassword());
        return nurse.writeNurseData ("src/Project1/myFile/SignInNurse.txt", data1, data2);
    }

    public String[] arrayOfString () {
        String[] text = new String[7];
        text[0] = user_name_text_field.getText ();
        text[1] = first_name_text_field.getText();
        text[2] = last_name_text_field.getText();
        text[3] = gender_text_field.getText();
        text[4] = age_text_field.getText();
        text[5] = degree_of_education_text_field.getText();
        text[6] = work_experience_text_field.getText();
        return text;
    }

    public void onClickRegisterButton () {
        Nurse nurse = new Nurse ();
        FilesAndTools myTool = new FilesAndTools ();
        int result6 = myTool.emptyTextFieldCheck (arrayOfString ());
        if (result6 == 0) { // if text field empty
            int result1 = nurse.createFile("src/Project1/myFile/SignInNurse.txt");
            if (result1 == 0) {
                nurse.writeFirstNurse("src/Project1/myFile/SignInNurse.txt");
            }
            int result3 = -1;
            String password = nurse.randomPassword();
            int result2 = nurse.checkNurseUserName("src/Project1/myFile/SignInNurse.txt", user_name_text_field.getText());
            if (result2 == 0 && (result1 == 0 || result1 == 1)) {
                result3 = getData(password);
            }
            String notification;
            if (result1 == 2 || result2 == 1 || result2 == 2 || result3 == 1) { // if have error
                notification = nurse.backStringError1(result1, result2, result3);
                myTool.audioPlayer("src/Project1/myAudio/notification.wav");
                myTool.createNotificationPage(notification, "/project1/myPicture/warning.png", "Error", 300, 150);
            } else { // else program doesnt have error
                notification = "Your Password Is : " + password;
                myTool.audioPlayer("src/Project1/myAudio/access.wav");
                myTool.createNotificationPage(notification, "/project1/myPicture/fileCheck.png", "Password", 500, 150);
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
        Stage primaryStage = (Stage) sign_in_label.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("myFXMLFile/login_nurse_page.fxml"));
        Scene myScene = new Scene (root);
        myScene.setFill (Color.TRANSPARENT);
        primaryStage.setScene (myScene);
        primaryStage.show();
    }
}
