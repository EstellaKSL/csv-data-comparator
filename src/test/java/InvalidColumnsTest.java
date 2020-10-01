import com.bos.training.Client;
import com.bos.training.CsvComparator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.assertTrue;

public class InvalidColumnsTest {
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    @Test
    public void whenMissingColumns() throws Exception {
        String file = "excelData\\JunitTestData\\invalidColumns\\missing_columns.csv";
        exceptionRule.expect(IOException.class);
        exceptionRule.expectMessage("Invalid File: Missing columns found in "+file+"");
        CsvComparator csvComparator = new CsvComparator();
        HashMap<Integer, String> list1 = new HashMap<>();
        ArrayList<String> columnsSelected = new ArrayList<>(Arrays.asList("all"));
        csvComparator.scanCsv(file, list1,columnsSelected);
    }
    @Test
    public void whenAdditionalColumns() throws Exception {
        String file = "excelData\\JunitTestData\\invalidColumns\\addition_columns.csv";
        exceptionRule.expect(IOException.class);
        exceptionRule.expectMessage("Invalid File: Additional columns found in "+file+"");
        CsvComparator csvComparator = new CsvComparator();
        HashMap<Integer, String> list1 = new HashMap<>();
        ArrayList<String> columnsSelected = new ArrayList<>(Arrays.asList("all"));
        csvComparator.scanCsv(file, list1,columnsSelected);
    }

    @Test
    public void whenCorrectColumns() throws Exception {
        String file = "excelData\\JunitTestData\\invalidColumns\\correct_columns.csv";
        CsvComparator csvComparator = new CsvComparator();
        HashMap<Integer, String> list1 = new HashMap<>();
        ArrayList<String> columnsSelected = new ArrayList<>(Arrays.asList("all"));
        csvComparator.scanCsv(file, list1,columnsSelected);
        HashMap<Integer, String> list2 = new HashMap<Integer, String>(){{
            put(1, String.valueOf(new Client("ID1","BOS12345","SGD","SAVINGS",10)));
            put(2, String.valueOf(new Client("ID2","BOS23456","SGD","SAVINGS",20)));
            put(3, String.valueOf(new Client("ID3","BOS34567","SGD","SAVINGS",30)));
        }};
        System.out.println(list1);
        System.out.println(list2);
        assertTrue(list2.equals(list1));
    }

}
