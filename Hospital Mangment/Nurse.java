package Project1;

import java.io.*;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Nurse extends Person {
    String userName;
    int degreeOfEducation,workExperience;

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
        String allChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
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

    public int checkNurseUserName (String fileName, String userName) {
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

    public int writeNurseData (String fileName, List<Integer> list1, List<String> list2) {
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

    public int writeFirstNurse (String fileName) {
        int result;
        try {
            File myFile = new File(fileName);
            FileOutputStream myStream = new FileOutputStream(myFile);
            OutputStreamWriter myWriter = new OutputStreamWriter(myStream);

            myWriter.write("Maryam" + "\n");
            myWriter.write("Arazkhani" + "\n");
            myWriter.write("woman" + "\n");
            myWriter.write("1001" + "\n");
            myWriter.write("zon7dIpw" + "\n");
            myWriter.write("36" + "\n");
            myWriter.write("17" + "\n");
            myWriter.write("10" + "\n");

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

    public int howManyNurse (String fileName) {
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

    public String backNurseName (String fileName, int numberOfPerson) {
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

    public int numberOfPatientForNurse (String fileName, String nurseName) {
        int counterLine = 0, counterPatient = 0;
        try {
            File myFile = new File(fileName);
            Scanner myScanner = new Scanner(myFile);
            while (myScanner.hasNextLine()) {
                counterLine++;
                String data = myScanner.nextLine();
                if (data.compareTo (nurseName) == 0 && counterLine % 3 == 2) {
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

    public String lowWorkNurse (String fileName1, String fileName2) {
        String result = "null";
        int minPatient = numberOfPatientForNurse (fileName2, backNurseName(fileName1, 1));
        if (howManyNurse (fileName1) == 1) {
            result = backNurseName (fileName1,1);
        }
        else {
            for (int i = 1; i < howManyNurse (fileName1); i++) {
                if (numberOfPatientForNurse (fileName2, backNurseName(fileName1, i + 1)) <= minPatient) {
                    minPatient = numberOfPatientForNurse (fileName2, backNurseName(fileName1, i + 1));
                    result = backNurseName (fileName1, i + 1);
                }
                else {
                    result = backNurseName (fileName1, 1);
                }
            }
        }
        return result;
    }

    public int settingLastUser (String fileName, String nationalCode) {
        int result = createFile (fileName);
        try {
            File myFile = new File(fileName);
            FileWriter myWriter = new FileWriter (myFile);

            myWriter.write (nationalCode);

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
                if (data.compareTo (gettingLastUser (fileName2)) == 0 && counter % 3 == 2) {
                    result = 0; // patient and nurse not matched
                    numLine = counter + 1;
                }
                if (result == 0 && numLine == counter && data.compareTo (nationalCode) == 0) {
                    result = 1; // patient and nurse matched
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

    public int writeVisitData (String fileName, List<String> list) {
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
            result = "Patient And Nurse Are Not Matched";
        }
        else if (result1 == 1) {
            result = "Patient And Nurse Are Matched";
        }
        else if (result1 == 2) {
            result = "Scanner Cant Read File";
        }
        else {
            result = "Unknown Error";
        }
        return result;
    }
}
