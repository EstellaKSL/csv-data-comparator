import com.bos.training.Client;
import com.bos.training.CsvComparator;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class CurrencyValidationTest {

    ArrayList<String> validCurrencyList = new ArrayList<String>(Arrays.asList("USD", "AUD", "INR", "CAD", "SGD", "CHF", "GBP", "HKD", "EUR", "SEK"));

    @Test(expected = IOException.class)
    public void whenCurrencyInvalid() throws IOException {
        CsvComparator csvComparator = new CsvComparator();
        HashMap<Integer, Client> list1 = new HashMap<Integer, Client>() {{
            put(1, new Client("ID1", "BOS2", "ABC", "SAVINGS", 5));
        }};
        csvComparator.validateCurrency(list1, validCurrencyList);
    }

}
