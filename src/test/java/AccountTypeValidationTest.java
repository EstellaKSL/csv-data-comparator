import com.bos.training.Client;
import com.bos.training.CsvComparator;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.assertTrue;

public class AccountTypeValidationTest {

    ArrayList<String> validAccountTypeList = new ArrayList<String>(Arrays.asList("SAVINGS", "CURRENT"));

    @Test(expected = IOException.class)
    public void whenAccTypeInvalid() throws IOException{
            CsvComparator csvComparator = new CsvComparator();
            HashMap<Integer, Client> list1 = new HashMap<Integer, Client>() {{
                put(1, new Client("ID1", "BOS2", "SGD", "ABC", 5));
            }};
            csvComparator.validateAccType(list1, validAccountTypeList);

    }
}