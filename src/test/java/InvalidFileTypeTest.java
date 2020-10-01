import com.bos.training.CsvComparator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class InvalidFileTypeTest {
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    @Test
    public void whenIncorrectFileType() throws Exception {
        exceptionRule.expect(IOException.class);
        exceptionRule.expectMessage("Invalid File Type: Only .csv file accepted");
        CsvComparator csvComparator = new CsvComparator();
        HashMap<Integer, String> list1 = new HashMap<>();
        ArrayList<String> columnsSelected = new ArrayList<>(Arrays.asList("all"));
        csvComparator.scanCsv("excelData\\JunitTestData\\invalidFileType\\sample_1.txt", list1,columnsSelected);
    }

}
