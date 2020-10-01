import com.bos.training.CsvComparator;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class FileNotFoundTest {

    @Test(expected = FileNotFoundException.class)
    public void whenFileNotFound() throws Exception {
        CsvComparator csvComparator = new CsvComparator();
        HashMap<Integer, String> list1 = new HashMap<>();
        ArrayList<String> columnsSelected = new ArrayList<>(Arrays.asList("all"));
        csvComparator.scanCsv("sample_file_not_found.csv", list1,columnsSelected);
    }
}