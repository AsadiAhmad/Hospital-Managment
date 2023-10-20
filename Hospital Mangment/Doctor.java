package Project1;

import java.io.*;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Doctor extends Person {
    String userName;
    int degreeOfEducation, workExperience;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getDegreeOfEducation() {
        return degreeOfEducation;
    }

    public void setDegreeOfEducation(int degreeOfEducation) {
        this.degreeOfEducation = degreeOfEducation;
    }

    public int getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(int workExperience) {
        this.workExperience = workExperience;
    }

    public String randomPassword () {
        String allChars = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rand = new Random ();
        char[] variable = new char[8];
        for (int counter = 0; counter < 8; counter++) {
            variable[counter] = allChars.charAt (rand.nextInt(62));
        }
        return String.valueOf (variable);
    }

    public int createFile (String fileName) {
        int result;
        try {
            File myFile = new File (fileName);
            if (myFile.createNewFile ()) {
                result = 0; // no problem to create file
            }
            else {
                result = 1; // file exist
            }
        }
        catch (IOException error) {
            result = 2; // error creating file
            error.printStackTrace();
        }
        return result;
    }

    public int checkDoctorUserName (String fileName, String userName) {
        int result = -1;
        int counter = 0;
        try {
            File myFile = new File(fileName);
            Scanner myScanner = new Scanner(myFile);
            while (myScanner.hasNextLine ()) {
                counter++;
                String data = myScanner.nextLine ();
                if (data.compareTo (userName) == 0 && counter % 8 == 4) {
                    result = 1; // userName exist
                    break;
                }
                else {
                    result = 0; // userName not exist
                }
            }
            myScanner.close();
        }
        catch (IOException error) {
            error.printStackTrace();
            result = 2; // Scanner cant read file
        }
        return result;
    }

    public int writeDoctorData (String fileName, List<Integer> list1, List<String> list2) {
        int result;
        try {
            FileWriter myWriter = new FileWriter (fileName, true);
            BufferedWriter myBuffer = new BufferedWriter (myWriter);
            for (String str: list2) {
                myBuffer.write (str + System.lineSeparator());
            }
            for (int num: list1) {
                myBuffer.write (num + System.lineSeparator());
            }
            myBuffer.close();
            myWriter.close();
            result = 0; // writing to file successfull
        }
        catch (IOException error) {
            error.printStackTrace();
            result = 1; // writing to file errors
        }
        return result;
    }

    public int checkUserAndPassword (String fileName, String userName, String Password) {
        int result = -1, result1 = -1, counter = 0, numLine = 0;
        try {
            File myFile = new File(fileName);
            Scanner myScanner = new Scanner(myFile);
            while (myScanner.hasNextLine ()) {
                counter++;
                String data = myScanner.nextLine ();
                if (data.compareTo (userName) == 0 && counter % 8 == 4) {
                    result = 0; // userName exist
                    result1 = 0;
                    numLine = counter + 1;
                }
                else if (result == 0 && numLine == counter && data.compareTo (Password) == 0) {
                    result = 1; // userName and password exist
                    break;
                }
                else {
                    result = 2; // userName not exist
                }
            }
            myScanner.close();
            if (result1 == 0 && result != 1) {
                result = 0;
            }
        }
        catch (IOException error) {
            error.printStackTrace();
            result = 3; // scanner cant read file
        }
        return result;
    }

    public String backStringError1 (int result1, int result2, int result3) {
        String result;
        if (result1 == 2) {
            result = "Creating File Errors";
        }
        else if (result2 == 1) {
            result = "UserName Exist";
        }
        else if (result2 == 2) {
            result = "Scanner Cant Read File";
        }
        else if (result3 == 1) {
            result = "Writing to File Errors";
        }
        else {
            result = "Unknown Error";
        }
        return result;
    }

    public String backStringError2 (int result1, int result2) {
        String result;
        if (result1 == 2) {
            result = "Creating File Errors";
        }
        else if (result2 == 0) {
            result = "Password Wrong !";
        }
        else if (result2 == 2) {
            result = "User Name Not Exist !";
        }
        else if (result2 == 3) {
            result = "Scanner Cant Read File";
        }
        else {
            result = "Unknown Error";
        }
        return result;
    }

    public int writeFirstDoctor (String fileName) {
        int result;
        try {
            File myFile = new File(fileName);
            FileOutputStream myStream = new FileOutputStream(myFile);
            OutputStreamWriter myWriter = new OutputStreamWriter(myStream);

            myWriter.write("Reza" + "\n");
            myWriter.write("Shakeri" + "\n");
            myWriter.write("Male" + "\n");
            myWriter.write("1001" + "\n");
            myWriter.write("zon7dIpw" + "\n");
            myWriter.write("50" + "\n");
            myWriter.write("17" + "\n");
            myWriter.write("25" + "\n");

            myWriter.close();
            myStream.close();
            result = 0; // writing to file successfull
        }
        catch (IOException error) {
            error.printStackTrace();
            result = 1; // writing to file errors
        }
        return result;
    }

    public int howManyDoctor (String fileName) {
        int counter = 0;
        try {
            File myFile = new File(fileName);
            Scanner myScanner = new Scanner(myFile);
            while (myScanner.hasNextLine ()) {
                counter++;
                String data = myScanner.nextLine ();
            }
            myScanner.close();
        }
        catch (IOException error) {
            error.printStackTrace();
        }
        return (counter / 8);
    }

    public String backDoctorName (String fileName, int numberOfPerson) {
        int counter = 0;
        String name = null;
        numberOfPerson = ((numberOfPerson - 1) * 8) + 4;
        try {
            File myFile = new File(fileName);
            Scanner myScanner = new Scanner(myFile);
            while (myScanner.hasNextLine()) {
                counter++;
                String data = myScanner.nextLine();
                if (numberOfPerson == counter) {
                    name = data;
                    break;
                }
            }
            myScanner.close();
        }
        catch (IOException error) {
            error.printStackTrace();
        }
        return name;
    }

    public int numberOfPatientForDoctor (String fileName, String doctorName) {
        int counterLine = 0, counterPatient = 0;
        try {
            File myFile = new File(fileName);
            Scanner myScanner = new Scanner(myFile);
            while (myScanner.hasNextLine()) {
                counterLine++;
                String data = myScanner.nextLine();
                if (data.compareTo (doctorName) == 0 && counterLine % 3 == 1) {
                    counterPatient++;
                }
            }
            myScanner.close();
        }
        catch (IOException error) {
            error.printStackTrace();
        }
        return counterPatient;
    }

    public String lowWorkDoctor (String fileName1, String fileName2) {
        String result = "null";
        int minPatient = numberOfPatientForDoctor (fileName2, backDoctorName(fileName1, 1));
        if (howManyDoctor (fileName1) == 1) {
            result = backDoctorName (fileName1,1);
        }
        else {
            for (int i = 1; i < howManyDoctor (fileName1); i++) {
                if (numberOfPatientForDoctor (fileName2, backDoctorName(fileName1, i + 1)) <= minPatient) {
                    minPatient = numberOfPatientForDoctor (fileName2, backDoctorName(fileName1, i + 1));
                    result = backDoctorName (fileName1, i + 1);
                }
                else {
                    result = backDoctorName (fileName1, 1);
                }
            }
        }
        return result;
    }

    public int settingLastUser (String fileName, String userName) {
        int result = createFile (fileName);
        try {
            File myFile = new File(fileName);
            FileWriter myWriter = new FileWriter (myFile);

            myWriter.write (userName);

            myWriter.close();
            result = 3; // writing to file successfull
        }
        catch (IOException error) {
            error.printStackTrace();
            result = 4; // writing to file errors
        }
        return result;
    }

    public String gettingLastUser (String fileName) {
        String result = "null";
        try {
            File myFile = new File(fileName);
            Scanner myScanner = new Scanner(myFile);
            while (myScanner.hasNextLine()) {
                String data = myScanner.nextLine();
                result = data;
            }
            myScanner.close();
        }
        catch (IOException error) {
            error.printStackTrace();
        }
        return result;
    }

    public int checkPatientNationalCode (String fileName1, String fileName2, String nationalCode) {
        int result = -1, counter = 0, numLine = 0;
        try {
            File myFile = new File(fileName1);
            Scanner myScanner = new Scanner(myFile);
            while (myScanner.hasNextLine ()) {
                counter++;
                String data = myScanner.nextLine ();
                if (data.compareTo (gettingLastUser (fileName2)) == 0 && counter % 3 == 1) {
                    result = 0; // patient and doctor not matched
                    numLine = counter + 2;
                }
                if (result == 0 && numLine == counter && data.compareTo (nationalCode) == 0) {
                    result = 1; // patient and doctor matched
                    break;
                }
            }
            myScanner.close();
        }
        catch (IOException error) {
            error.printStackTrace();
            result = 2; // scanner cant read file
        }
        return result;
    }

    public int writeSurgeryData (String fileName, List<String> list) {
        int result;
        try {
            FileWriter myWriter = new FileWriter (fileName, true);
            BufferedWriter myBuffer = new BufferedWriter (myWriter);
            for (String str: list) {
                myBuffer.write (str + System.lineSeparator());
            }
            myBuffer.close();
            myWriter.close();
            result = 0; // writing to file successfull
        }
        catch (IOException error) {
            error.printStackTrace();
            result = 1; // writing to file errors
        }
        return result;
    }

    public String backStringError3 (int result1) {
        String result;
        if (result1 == 0) {
            result = "Patient And Doctor Are Not Matched";
        }
        else if (result1 == 1) {
            result = "Patient And Doctor Are Matched";
        }
        else if (result1 == 2) {
            result = "Scanner Cant Read File";
        }
        else {
            result = "Unknown Error";
        }
        return result;
    }

    public int checkNationalCodeForDischarge (String fileName, String nationalCode) {
        int result = -1;
        int counter = 0;
        try {
            File myFile = new File(fileName);
            Scanner myScanner = new Scanner(myFile);
            while (myScanner.hasNextLine ()) {
                counter++;
                String data = myScanner.nextLine ();
                if (data.compareTo (nationalCode) == 0) {
                    result = 1; // nationalCode exist
                    break;
                }
                else {
                    result = 0; // nationalCode not exist
                }
            }
            myScanner.close();
        }
        catch (IOException error) {
            error.printStackTrace();
            result = 2; // Scanner cant read file
        }
        return result;
    }

    public int writeFirstDischarge (String fileName) {
        int result;
        try {
            File myFile = new File(fileName);
            FileOutputStream myStream = new FileOutputStream(myFile);
            OutputStreamWriter myWriter = new OutputStreamWriter(myStream);

            myWriter.write("7894561230" + "\n");

            myWriter.close();
            myStream.close();
            result = 0; // writing to file successfull
        }
        catch (IOException error) {
            error.printStackTrace();
            result = 1; // writing to file errors
        }
        return result;
    }

    public String backStringError4 (int result1,int result2) {
        String result;
        if (result1 == 0) {
            result = "Patient And Doctor Are Not Matched";
        }
        else if (result1 == 2) {
            result = "Scanner Cant Read File";
        }
        else if (result2 == 1) {
            result = "This Patient is already discharge";
        }
        else {
            result = "Unknown Error";
        }
        return result;
    }
}
