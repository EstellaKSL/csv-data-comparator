import com.bos.training.Client;
import com.bos.training.CsvComparator;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.assertTrue;

public class CompareBalanceTest {

    @Test
    public void whenBalanceDiffers() {

        CsvComparator csvComparator = new CsvComparator();
        HashMap<Integer, String> list1 = new HashMap<Integer, String>(){{
            put(1, String.valueOf(new Client("ID1","BOS2","SGD","SAVINGS",5)));
        }};
        HashMap<Integer, String> list2 = new HashMap<Integer, String>() {{
            put(1, String.valueOf(new Client("ID1","BOS2","SGD","SAVINGS",50)));
        }};
        ArrayList<String> actualExceptionList = new ArrayList<String>();
        try {
            csvComparator.csvComparator(list1, list2, actualExceptionList);
        } catch(IOException e){}

        ArrayList<String> expectedExceptionList = new ArrayList<String>(Arrays.asList("ID1,BOS2,SGD,SAVINGS,5", "ID1,BOS2,SGD,SAVINGS,50"));

        System.out.println(actualExceptionList);
        System.out.println(expectedExceptionList);

        assertTrue(expectedExceptionList.equals(actualExceptionList));
    }

}
