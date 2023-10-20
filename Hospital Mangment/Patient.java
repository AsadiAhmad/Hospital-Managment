package Project1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Patient {
    String nationalCode, Password;

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
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

    public int checkCodeAndPassword (String fileName, String nationalCode, String Password) {
        int result = -1, result1 = -1, counter = 0, numLine = 0;
        try {
            File myFile = new File(fileName);
            Scanner myScanner = new Scanner(myFile);
            while (myScanner.hasNextLine ()) {
                counter++;
                String data = myScanner.nextLine ();
                if (data.compareTo (nationalCode) == 0 && counter % 8 == 6) {
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

    public String backStringError (int result1, int result2, int result4) {
        String result;
        if (result1 == 2) {
            result = "Creating File Errors";
        }
        else if (result2 == 0) {
            result = "Password Wrong !";
        }
        else if (result2 == 2) {
            result = "National Code Not Exist !";
        }
        else if (result2 == 3) {
            result = "Scanner Cant Read File";
        }
        else if (result4 == 1) {
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
            result = 0; // writing to file successfull
        }
        catch (IOException error) {
            error.printStackTrace();
            result = 1; // writing to file errors
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

    public int counterOfLines (String fileName1, String fileName2) {
        int lineCounter = 0, resultCounter = 0;
        try {
            File myFile = new File(fileName1);
            Scanner myScanner = new Scanner(myFile);
            while (myScanner.hasNextLine ()) {
                lineCounter++;
                String data = myScanner.nextLine ();
                if (data.compareTo (gettingLastUser (fileName2)) == 0 && lineCounter % 5 == 1) {
                    resultCounter += 5;
                }
            }
            myScanner.close();
        }
        catch (IOException error) {
            error.printStackTrace();
        }
        return resultCounter;
    }

    public ArrayList<String> createListSurgery (String fileName1, String fileName2) {
        ArrayList<String> myList = new ArrayList<String>();
        int result = -1, counter = 0, numLine = 0, lineCounter = 0;
        try {
            File myFile = new File(fileName1);
            Scanner myScanner = new Scanner(myFile);
            while (myScanner.hasNextLine ()) {
                counter++;
                String data = myScanner.nextLine ();
                if (data.compareTo (gettingLastUser (fileName2)) == 0 && counter % 5 == 1) {
                    numLine = counter;
                    result = 0;
                    myList.add ("National Code :" + data);
                }
                if (numLine + 1 == counter && result == 0) {
                    myList.add ("Status Of Patient :" + data);
                }
                if (numLine + 2 == counter && result == 0) {
                    myList.add ("Cost Of Surgery :" + data);
                }
                if (numLine + 3 == counter && result == 0) {
                    myList.add ("Time Of Surgery :" + data);
                }
                if (numLine + 4 == counter && result == 0) {
                    myList.add ("Description Of Surgery :" + data);
                }
            }
            myScanner.close();
        }
        catch (IOException error) {
            error.printStackTrace();
        }
        return myList;
    }

    public ArrayList<String> createListVisit (String fileName1, String fileName2) {
        ArrayList<String> myList = new ArrayList<String>();
        int result = -1, counter = 0, numLine = 0, lineCounter = 0;
        try {
            File myFile = new File(fileName1);
            Scanner myScanner = new Scanner(myFile);
            while (myScanner.hasNextLine ()) {
                counter++;
                String data = myScanner.nextLine ();
                if (data.compareTo (gettingLastUser (fileName2)) == 0 && counter % 5 == 1) {
                    numLine = counter;
                    result = 0;
                    myList.add ("National Code :" + data);
                }
                if (numLine + 1 == counter && result == 0) {
                    myList.add ("Status Of Patient :" + data);
                }
                if (numLine + 2 == counter && result == 0) {
                    myList.add ("Cost Of Visit :" + data);
                }
                if (numLine + 3 == counter && result == 0) {
                    myList.add ("Time Of Visit :" + data);
                }
                if (numLine + 4 == counter && result == 0) {
                    myList.add ("Description Of Visit :" + data);
                }
            }
            myScanner.close();
        }
        catch (IOException error) {
            error.printStackTrace();
        }
        return myList;
    }

    public ArrayList<String> createListDiagnosis (String fileName1, String fileName2) {
        ArrayList<String> myList = new ArrayList<String>();
        int result = -1, counter = 0, numLine = 0, lineCounter = 0;
        try {
            File myFile = new File(fileName1);
            Scanner myScanner = new Scanner(myFile);
            while (myScanner.hasNextLine ()) {
                counter++;
                String data = myScanner.nextLine ();
                if (data.compareTo (gettingLastUser (fileName2)) == 0 && counter % 5 == 1) {
                    numLine = counter;
                    result = 0;
                    myList.add ("National Code :" + data);
                }
                if (numLine + 1 == counter && result == 0) {
                    myList.add ("Status Of Patient :" + data);
                }
                if (numLine + 2 == counter && result == 0) {
                    myList.add ("Cost Of Diagnosis :" + data);
                }
                if (numLine + 3 == counter && result == 0) {
                    myList.add ("Time Of Diagnosis :" + data);
                }
                if (numLine + 4 == counter && result == 0) {
                    myList.add ("Description Of Diagnosis :" + data);
                }
            }
            myScanner.close();
        }
        catch (IOException error) {
            error.printStackTrace();
        }
        return myList;
    }

    public ArrayList<String> createListDiagnosisCost (String fileName1, String fileName2, int state) {
        ArrayList<String> myList = new ArrayList<String>();
        int counter = 0, numLine = 0;
        try {
            File myFile = new File(fileName1);
            Scanner myScanner = new Scanner(myFile);
            while (myScanner.hasNextLine ()) {
                counter++;
                String data = myScanner.nextLine ();
                if (data.compareTo (gettingLastUser (fileName2)) == 0 && counter % 5 == 1) {
                    numLine = counter + 2;
                }
                if (numLine == counter) {
                    if (state == 1) {
                        myList.add ("Cost Of Diagnosis :" + data);
                    }
                    if (state == 2) {
                        myList.add ("Cost Of Surgery :" + data);
                    }
                    if (state == 3) {
                        myList.add ("Cost Of Visit :" + data);
                    }
                }
            }
            myScanner.close();
        }
        catch (IOException error) {
            error.printStackTrace();
        }
        return myList;
    }

    public int counterOfLines2 (String fileName1, String fileName2) {
        int lineCounter = 0, resultCounter = 0;
        try {
            File myFile = new File(fileName1);
            Scanner myScanner = new Scanner(myFile);
            while (myScanner.hasNextLine ()) {
                lineCounter++;
                String data = myScanner.nextLine ();
                if (data.compareTo (gettingLastUser (fileName2)) == 0 && lineCounter % 5 == 1) {
                    resultCounter += 1;
                }
            }
            myScanner.close();
        }
        catch (IOException error) {
            error.printStackTrace();
        }
        return resultCounter;
    }

    public int checkNationalCodeForPayBill (String fileName, String nationalCode) {
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

    public int writeBillData (String fileName, List<String> list) {
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

    public String backStringError2 (int result2) {
        String result;
        if (result2 == 2) {
            result = "Scanner Cant Read File";
        }
        else if (result2 == 1) {
            result = "This Patient is already Payed The Bill";
        }
        else {
            result = "Unknown Error";
        }
        return result;
    }

    public int costCount (String fileName1, String fileName2) {
        int counter = 0, numLine = 0, costCount = 0;
        try {
            File myFile = new File(fileName1);
            Scanner myScanner = new Scanner(myFile);
            while (myScanner.hasNextLine ()) {
                counter++;
                String data = myScanner.nextLine ();
                if (data.compareTo (gettingLastUser (fileName2)) == 0 && counter % 5 == 1) {
                    numLine = counter + 2;
                }
                if (numLine == counter) {
                    costCount += Integer.parseInt (data);
                }
            }
            myScanner.close();
        }
        catch (IOException error) {
            error.printStackTrace();
        }
        return costCount;
    }

    public String costCountAll (String fileName1, String fileName2, String fileName3, String fileName4) {
        Patient patient = new Patient ();
        int costCountAll = 0;
        costCountAll += patient.costCount (fileName1, fileName4);
        costCountAll += patient.costCount (fileName2, fileName4);
        costCountAll += patient.costCount (fileName3, fileName4);
        return String.valueOf(costCountAll);
    }
}
