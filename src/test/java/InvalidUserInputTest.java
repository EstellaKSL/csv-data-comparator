import com.bos.training.CsvComparator;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;

import static org.junit.Assert.assertTrue;


public class InvalidUserInputTest {

    //validUserInputList is "accountno","currency", "accounttype", "balance", "all". This is checked in ValidateUserInput method in CsvComparator.java

    @Test(expected = InputMismatchException.class)
    public void whenUserInputInvalid() throws InputMismatchException {
        CsvComparator csvComparator = new CsvComparator();
        String[] testInput = {"wrongColumnName1","wrongColumnName2"};
        ArrayList<String> columnsSelected = new ArrayList<String>(Arrays.asList(testInput));
        csvComparator.validateUserInput(columnsSelected);
    }

    @Test
    public void checkColumnsReturnCorrectly() throws IOException {
        CsvComparator csvComparator = new CsvComparator();
        String[] testInputs = {"all"};
        String[] testInputs2 = {"accountno", "currency","accounttype","balance"};
        ArrayList<String> columnsSetSelected = new ArrayList<String>(Arrays.asList(testInputs));
        ArrayList<String> columnsSetSelected2 = new ArrayList<String>(Arrays.asList(testInputs2));
        String actualOneException = csvComparator.userInputs(columnsSetSelected,"ID123","BOS2982","SGD","SAVINGS",1234);
        String actualTwoException = csvComparator.userInputs(columnsSetSelected2,"ID123","BOS2982","SGD","SAVINGS",1234);
        String expectedException = "ID123,BOS2982,SGD,SAVINGS,1234";
        System.out.println(actualOneException);
        System.out.println(actualTwoException);
        System.out.println(expectedException);
        assertTrue(expectedException.equals(actualOneException));
        assertTrue(expectedException.equals(actualTwoException));


    }

}
