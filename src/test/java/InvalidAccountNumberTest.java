import com.bos.training.CsvComparator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.IOException;

public class InvalidAccountNumberTest {
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    @Test
    public void whenIncorrectFileType() throws Exception {
        exceptionRule.expect(IOException.class);
        exceptionRule.expectMessage("Invalid Account Number: BOS9802 found in samplefile.csv. Required min of 5 digit and max of 9 digit");
        CsvComparator csvComparator = new CsvComparator();
        csvComparator.validateAccount("BOS9802", new File("samplefile.csv"));
    }
}
