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

public class SignInDoctorPageController {
    public Label sign_in_label;
    public TextField first_name_text_field;
    public TextField last_name_text_field;
    public TextField user_name_text_field;
    public TextField age_text_field;
    public TextField gender_text_field;
    public TextField degree_of_education_text_field;
    public TextField work_experience_text_field;
    public Button register_button;
    public Button cancel_button;

    public int getData (String password) {
        Doctor doctor = new Doctor ();
        doctor.setUserName (user_name_text_field.getText ());
        doctor.setFirstName (first_name_text_field.getText ());
        doctor.setLastName (last_name_text_field.getText ());
        doctor.setGender (gender_text_field.getText ());
        doctor.setAge (Integer.parseInt (age_text_field.getText ()));
        doctor.setDegreeOfEducation (Integer.parseInt (degree_of_education_text_field.getText ()));
        doctor.setWorkExperience (Integer.parseInt(work_experience_text_field.getText ()));
        doctor.setPassword (password);
        List<Integer> data1 = new ArrayList<>();
        List<String> data2 = new ArrayList<>();
        data1.add (doctor.getAge());
        data1.add (doctor.getDegreeOfEducation());
        data1.add (doctor.getWorkExperience());
        data2.add (doctor.getFirstName());
        data2.add (doctor.getLastName());
        data2.add (doctor.getGender());
        data2.add (doctor.getUserName());
        data2.add (doctor.getPassword());
        return doctor.writeDoctorData ("src/Project1/myFile/SignInDoctor.txt", data1, data2);
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

    public void onClickRegister () throws IOException {
        Doctor doctor = new Doctor ();
        FilesAndTools myTool = new FilesAndTools ();
        int result6 = myTool.emptyTextFieldCheck (arrayOfString ());
        if (result6 == 0) { // if text field empty
            int result1 = doctor.createFile("src/Project1/myFile/SignInDoctor.txt");
            if (result1 == 0) {
                doctor.writeFirstDoctor("src/Project1/myFile/SignInDoctor.txt");
            }
            int result3 = -1;
            String password = doctor.randomPassword();
            int result2 = doctor.checkDoctorUserName("src/Project1/myFile/SignInDoctor.txt", user_name_text_field.getText());
            if (result2 == 0 && (result1 == 0 || result1 == 1)) {
                result3 = getData(password);
            }
            String notification;
            if (result1 == 2 || result2 == 1 || result2 == 2 || result3 == 1) { // if have error
                notification = doctor.backStringError1(result1, result2, result3);
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
        Parent root = FXMLLoader.load(getClass().getResource("myFXMLFile/login_doctor_page.fxml"));
        Scene myScene = new Scene (root);
        myScene.setFill (Color.TRANSPARENT);
        primaryStage.setScene (myScene);
        primaryStage.show();
    }
}
