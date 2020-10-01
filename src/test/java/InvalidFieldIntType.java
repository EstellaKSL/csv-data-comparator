import com.bos.training.CsvComparator;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class InvalidFieldIntType {
    @Test(expected=NumberFormatException.class)
    public void whenIncorrectTypeForBalance() throws Exception {
        CsvComparator csvComparator = new CsvComparator();
        HashMap<Integer, String> list1 = new HashMap<>();
        ArrayList<String> columnsSelected = new ArrayList<>(Arrays.asList("all"));
        csvComparator.scanCsv("excelData\\JunitTestData\\invalidFieldType\\invalid_balance_type.csv", list1, columnsSelected);
    }
    @Test(expected=NumberFormatException.class)
    public void whenIncorrectTypeForID() throws Exception {
        CsvComparator csvComparator = new CsvComparator();
        HashMap<Integer, String> list1 = new HashMap<>();
        ArrayList<String> columnsSelected = new ArrayList<>(Arrays.asList("all"));
        csvComparator.scanCsv("excelData\\JunitTestData\\invalidFieldType\\invalid_id_type.csv", list1,columnsSelected);
    }

    @Test(expected = NumberFormatException.class)
    public void whenIncorrectTypeForAccountNumber() throws  Exception {
        CsvComparator csvComparator = new CsvComparator();
        csvComparator.validateAccount("BOS33TEEFW", new File("sample_file.csv"));
    }
}
