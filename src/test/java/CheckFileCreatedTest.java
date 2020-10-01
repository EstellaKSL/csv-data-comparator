import com.bos.training.CsvComparator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.assertTrue;

public class CheckFileCreatedTest {
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    @Test
    public void whenFileCreated() throws Exception {
        CsvComparator csvComparator = new CsvComparator();
        ArrayList<String> columnsSelected = new ArrayList<>(Arrays.asList("all"));
        csvComparator.writeCsv(columnsSelected, "output");
        File file = new File("output.csv");
        assertTrue(file.exists());
    }

}
