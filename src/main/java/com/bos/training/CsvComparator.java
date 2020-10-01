package com.bos.training;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


public class CsvComparator {

    // placing out so that we can run Junit on the csvComparator and scanCsv
    // main do nth but call method
    public static void main(String[] args) throws IOException {

        String csvFile1 = "excelData\\sample_file_1.csv";
        String csvFile2 = "excelData\\sample_file_3.csv";
        String outputFile = "output.csv";

        // hashmap to set key:: values
        HashMap<Integer, String> list1 = new HashMap<>();
        HashMap<Integer, String> list2 = new HashMap<>();

        ArrayList<String> columnsSelected = getUserInput();

        // storing of exceptions
        ArrayList<String> exceptionList = new ArrayList<String>();

        scanCsv(csvFile1,list1,columnsSelected);
        scanCsv(csvFile2,list2,columnsSelected);

        csvComparator(list1,list2, exceptionList);
        writeCsv(exceptionList, outputFile);
    }

    public static ArrayList<String> getUserInput(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter columns (AccountNo, Currency, AccountType, Balance, All) to compare: ");
        String userInputs = input.nextLine().toLowerCase().replaceAll("\\s+","");
        input.close();
        ArrayList<String> columnsSelected = new ArrayList<String>(Arrays.asList(userInputs.split(",")));
        validateUserInput(columnsSelected);
        return columnsSelected;
    }

    public static void validateUserInput(ArrayList<String> columnsSelected) throws InputMismatchException {
        for (int i=0; i<columnsSelected.size(); i++) {
            String columnName = columnsSelected.get(i);
            if (!columnName.equals("accountno") && !columnName.equals("currency") && !columnName.equals("accounttype") && !columnName.equals("balance") && !columnName.equals("all")) {
                throw new InputMismatchException();
            }
        }
    }

    public static String userInputs(ArrayList<String> columnsSelected, String customerId, String accountNo, String currency, String accountType, Integer balance) throws IOException {

        String columnsIdentified = ""+customerId+"";

        if(columnsSelected.contains("accountno")){
         columnsIdentified = columnsIdentified + ","+accountNo+"";

        }
        if(columnsSelected.contains("currency")){
            columnsIdentified = columnsIdentified + ","+currency+"";
        }
        if(columnsSelected.contains("accounttype")){
            columnsIdentified = columnsIdentified + ","+accountType+"";
        }
        if(columnsSelected.contains("balance")){
            columnsIdentified = columnsIdentified + ","+balance+"";
        }
        if(columnsSelected.contains("all")){
            columnsIdentified = String.valueOf(new Client(customerId,accountNo,currency,accountType,balance));
        }

        return columnsIdentified;

    }

    //method to scan file
    public static void scanCsv(String csvFile, HashMap<Integer, String> list,ArrayList<String> columnsSelected ) throws IOException {

        String line = "";
        String cvsSplitBy = ",";

        File file = new File(csvFile);
        String fileType = getFileExtension(file);

        if (!fileType.equals("csv")) {
            throw new IOException("Invalid File Type: Only .csv file accepted");}



        try (Scanner sc = new Scanner(file)) {
            sc.nextLine();
            while (sc.hasNext()) {
                line = sc.next();
                // use comma as separator
                String[] client = line.replaceAll("\"", "").split(cvsSplitBy);
                // set primary key integer to allow sorting according to id number
                int primaryKey = Integer.parseInt(client[0].replaceAll("ID",""));
                int columnSize = client.length;
                if (columnSize == 5) {
                // validate type
                String customerId = client[0];
                String accountNo = validateAccount(client[1], file);
                String currency = client[2];
                String accountType = client[3];
                int balance = Integer.parseInt(client[4]);

                // place into hashmap for key:value
                    list.put(primaryKey,  userInputs(columnsSelected,customerId, accountNo, currency, accountType,balance));
                } else if (columnSize < 5){
                    throw new IOException("Invalid File: Missing columns found in "+file+"");
                } else if (columnSize > 5){
                    throw new IOException("Invalid File: Additional columns found in "+file+"");
                }


            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: Could not find file.");
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public static String validateAccount(String account, File file) throws IOException {
        String accountNumber = account.replaceAll("BOS","");
        int accountNum = Integer.parseInt(accountNumber);
        if(accountNumber.length() > 9 | accountNumber.length() < 5) {
            throw new IOException("Invalid Account Number: BOS"+accountNum+" found in "+file+". Required min of 5 digit and max of 9 digit");
        } else return account;
    }

    public void validateAccType(HashMap<Integer, Client> list1, ArrayList<String> validAccountTypeList) throws IOException {

        String[] inputAccType = new String[list1.size()];

        for (int i = 0; i < list1.size(); i++) {
            inputAccType[i] = list1.get(i + 1).getCurrency();
        }

        for (int i = 0; i < inputAccType.length; i++) {
            if (!validAccountTypeList.contains(inputAccType[i])) {
                throw new IOException("Invalid Account Type: " + inputAccType[i] + " found in row index " + i + ". Account type should only be 'SAVINGS' or 'CURRENT'.");
            }
        }
    }

    public void validateCurrency(HashMap<Integer, Client> list1, ArrayList<String> validCurrencyList) throws IOException{
        String[] inputCurrencyList = new String[list1.size()];

        for (int i=0; i<list1.size();i++) {
            inputCurrencyList[i] = list1.get(i+1).getCurrency();
        }

        for(int i=0; i<inputCurrencyList.length; i++) {
            if (!validCurrencyList.contains(inputCurrencyList[i])) {
                throw new IOException("Invalid Account Type: " + inputCurrencyList[i] + " found in row index " + i + ".");
            }
        }
    }

    public static void csvComparator(HashMap<Integer, String> list1, HashMap<Integer, String> list2, ArrayList<String> exceptionList) throws IOException {

        for (Integer i: list1.keySet()){
            if (!String.valueOf(list1.get(i)).equals(String.valueOf(list2.get(i)))){
                exceptionList.add(String.valueOf(list1.get(i)));
                exceptionList.add(String.valueOf(list2.get(i)));
            }
        }
    }

    public static void writeCsv(ArrayList<String> exceptionList, String outputFile) throws IOException {

        PrintWriter writer  = null;

        try {writer = new PrintWriter(new File(outputFile));
            System.out.println("output.csv created");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //write contents of exceptionList into "output.csv"
        for (String str : exceptionList)
        {
            // To check what is printed to the output file:
            System.out.println(str);

            //writing each line of the exceptionList to the output file
            writer.write(str);
            writer.write("\n");
        }

        //close the scanners
        writer.close();

    }

    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }

}



