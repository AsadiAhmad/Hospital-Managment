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

public class AccountingPageController {
    public Label national_code_label;
    public TextField first_name_text_field;
    public TextField last_name_text_field;
    public TextField national_code_text_field;
    public TextField age_text_field;
    public TextField gender_text_field;
    public TextArea description_illness_text_field;
    public TextArea history_of_specific_illness_text_field;
    public Button register_button;
    public Button cancel_button;

    public int getData (String password) {
        Accounting account = new Accounting ();
        account.setNationalCode (national_code_text_field.getText ());
        account.setFirstName (first_name_text_field.getText ());
        account.setLastName (last_name_text_field.getText ());
        account.setGender (gender_text_field.getText ());
        account.setAge (Integer.parseInt (age_text_field.getText ()));
        account.setDescriptionIllness (description_illness_text_field.getText ());
        account.setHistoryOfSpecificIllness (history_of_specific_illness_text_field.getText ());
        account.setPassword (password);
        List<Integer> data1 = new ArrayList<>();
        List<String> data2 = new ArrayList<>();
        data1.add (account.getAge());
        data2.add (account.getDescriptionIllness());
        data2.add (account.getHistoryOfSpecificIllness());
        data2.add (account.getFirstName());
        data2.add (account.getLastName());
        data2.add (account.getGender());
        data2.add (account.getNationalCode());
        data2.add (account.getPassword());
        return account.writePatientData ("src/Project1/myFile/SignInPatient.txt", data1, data2);
    }

    public String[] arrayOfString () {
        String[] text = new String[7];
        text[0] = national_code_text_field.getText ();
        text[1] = first_name_text_field.getText ();
        text[2] = last_name_text_field.getText ();
        text[3] = gender_text_field.getText ();
        text[4] = age_text_field.getText ();
        text[5] = description_illness_text_field.getText ();
        text[6] = history_of_specific_illness_text_field.getText ();
        return text;
    }

    public void onClickRegisterButton () {
        Accounting account = new Accounting ();
        FilesAndTools myTool = new FilesAndTools ();
        int result6 = myTool.emptyTextFieldCheck (arrayOfString ());
        if (result6 == 0) { // if text field empty
            int result1 = account.createFile("src/Project1/myFile/SignInPatient.txt");
            if (result1 == 0) {
                account.writeFirstPatient("src/Project1/myFile/SignInPatient.txt");
            }
            account.writeFirstGroup("src/Project1/myFile/SignInDoctor.txt", "src/Project1/myFile/SignInNurse.txt", "src/Project1/myFile/SignInPatient.txt");
            int result4 = account.writeFirstGroupTherapy("src/Project1/myFile/GroupTherapy.txt");
            int result3 = -1;
            String password = account.randomPassword();
            int result5 = -1;
            int result2 = account.checkPatientNationalCode("src/Project1/myFile/SignInPatient.txt", national_code_text_field.getText());
            if (result2 != 3) {
                result5 = account.groupTherapy("src/Project1/myFile/SignInDoctor.txt", "src/Project1/myFile/SignInNurse.txt", "src/Project1/myFile/GroupTherapy.txt", national_code_text_field.getText ());
            }
            if (result2 == 0 && (result1 == 0 || result1 == 1)) {
                result3 = getData(password);
            }
            String notification;
            if (result1 == 2 || result2 == 1 || result2 == 2 || result2 == 3 || result3 == 1 || result4 == 1 || result5 == 1) { // if have error
                notification = account.backStringError(result1, result2, result3, result4, result5);
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

    public void onClickCancel () throws IOException {
        FilesAndTools myTool = new FilesAndTools ();
        myTool.audioPlayer ("src/Project1/myAudio/click.wav");
        Stage primaryStage = (Stage) national_code_label.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("myFXMLFile/main_page.fxml"));
        Scene myScene = new Scene (root);
        myScene.setFill (Color.TRANSPARENT);
        primaryStage.setScene (myScene);
        primaryStage.show();
    }
}
