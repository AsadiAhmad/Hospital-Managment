package Project1;

import java.io.*;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Accounting extends Person {
    String nationalCode, descriptionIllness, historyOfSpecificIllness;

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getDescriptionIllness() {
        return descriptionIllness;
    }

    public void setDescriptionIllness(String descriptionIllness) {
        this.descriptionIllness = descriptionIllness;
    }

    public String getHistoryOfSpecificIllness() {
        return historyOfSpecificIllness;
    }

    public void setHistoryOfSpecificIllness(String historyOfSpecificIllness) {
        this.historyOfSpecificIllness = historyOfSpecificIllness;
    }

    public String randomPassword () {
        String allChars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
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

    public int checkPatientNationalCode (String fileName, String nationalCode) {
        int result = -1;
        int counter = 0;
        try {
            File myFile = new File(fileName);
            Scanner myScanner = new Scanner(myFile);
            while (myScanner.hasNextLine ()) {
                counter++;
                String data = myScanner.nextLine ();
                if (data.compareTo (nationalCode) == 0 && counter % 8 == 6) {
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
        if (nationalCode.length() != 10) {
            result = 3;
        }
        return result;
    }

    public int writePatientData (String fileName, List<Integer> list1, List<String> list2) {
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

    public String backStringError (int result1, int result2, int result3, int result4, int result5) {
        String result;
        if (result1 == 2) {
            result = "Creating File Errors";
        }
        else if (result2 == 1) {
            result = "National Code Exist";
        }
        else if (result2 == 2) {
            result = "Scanner Cant Read File";
        }
        else if (result2 == 3) {
            result = "nationalCode Must Have 10 Digit";
        }
        else if (result3 == 1 || result4 == 1 || result5 == 1) {
            result = "Writing to File Errors";
        }
        else {
            result = "Unknown Error";
        }
        return result;
    }

    public int writeFirstPatient (String fileName) {
        int result;
        try {
            File myFile = new File(fileName);
            FileOutputStream myStream = new FileOutputStream(myFile);
            OutputStreamWriter myWriter = new OutputStreamWriter(myStream);

            myWriter.write("headache" + "\n");
            myWriter.write("diabet" + "\n");
            myWriter.write("Ali" + "\n");
            myWriter.write("Karimi" + "\n");
            myWriter.write("Male" + "\n");
            myWriter.write("7894561230" + "\n");
            myWriter.write("sRdlx1oC" + "\n");
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

    public void writeFirstGroup (String fileName1, String fileName2, String fileName3) {
        Doctor doctor = new Doctor ();
        Nurse nurse = new Nurse ();
        Accounting account = new Accounting ();
        int result1 = doctor.createFile (fileName1);
        int result2 = nurse.createFile (fileName2);
        int result3 = account.createFile (fileName3);
        if (result1 == 0) {
            doctor.writeFirstDoctor (fileName1);
        }
        if (result2 == 0) {
            nurse.writeFirstNurse (fileName2);
        }
        if (result3 == 0) {
            account.writeFirstPatient (fileName3);
        }
    }

    public int writeFirstGroupTherapy (String fileName) {
        Accounting account = new Accounting ();
        int result1 = account.createFile (fileName);
        int result = -1;
        if (result1 == 0) {
            try {
                File myFile = new File(fileName);
                FileOutputStream myStream = new FileOutputStream(myFile);
                OutputStreamWriter myWriter = new OutputStreamWriter(myStream);

                myWriter.write("1001" + "\n");
                myWriter.write("1001" + "\n");
                myWriter.write("7894561230" + "\n");

                myWriter.close();
                myStream.close();
                result = 0; // writing to file successfull
            } catch (IOException error) {
                error.printStackTrace();
                result = 1; // writing to file errors
            }
        }
        return result;
    }

    public int groupTherapy (String fileName1, String fileName2, String fileName3, String patientNationalCode) {
        int result;
        Doctor doctor = new Doctor ();
        Nurse nurse = new Nurse ();
        try {
            File myFile = new File (fileName3);
            FileWriter myWriter = new FileWriter (myFile, true);
            BufferedWriter myBuffer = new BufferedWriter (myWriter);

            myBuffer.write(doctor.lowWorkDoctor (fileName1, fileName3) + "\n");
            myBuffer.write(nurse.lowWorkNurse (fileName2, fileName3) + "\n");
            myBuffer.write(patientNationalCode + "\n");

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
}
