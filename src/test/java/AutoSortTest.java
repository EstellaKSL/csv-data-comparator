import com.bos.training.Client;
import com.bos.training.CsvComparator;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.assertTrue;

public class AutoSortTest {

        @Test
        public void whenPrimaryKeyNotSorted() {

            CsvComparator csvComparator = new CsvComparator();
            HashMap<Integer, String> list1 = new HashMap<Integer, String>(){{
                put(2, String.valueOf(new Client("ID2","BOS2","SGD","SAVINGS",20)));
                put(3, String.valueOf(new Client("ID3","BOS3","SGD","SAVINGS",100)));
                put(1, String.valueOf(new Client("ID1","BOS1","SGD","SAVINGS",5)));
            }};
            HashMap<Integer, String> list2 = new HashMap<Integer, String>(){{
                put(3, String.valueOf(new Client("ID3","BOS3","SGD","SAVINGS",60)));
                put(1, String.valueOf(new Client("ID1","BOS1","SGD","SAVINGS",50)));
                put(2, String.valueOf(new Client("ID2","BOS2","SGD","SAVINGS",10)));
            }};
            ArrayList<String> actualExceptionList = new ArrayList<String>();
            try {
                csvComparator.csvComparator(list1, list2, actualExceptionList);
            } catch(IOException e){}

            ArrayList<String> expectedExceptionList = new ArrayList<String>(Arrays.asList("ID1,BOS1,SGD,SAVINGS,5", "ID1,BOS1,SGD,SAVINGS,50","ID2,BOS2,SGD,SAVINGS,20","ID2,BOS2,SGD,SAVINGS,10","ID3,BOS3,SGD,SAVINGS,100","ID3,BOS3,SGD,SAVINGS,60"));

            System.out.println(actualExceptionList);
            System.out.println(expectedExceptionList);

            assertTrue(expectedExceptionList.equals(actualExceptionList));
        }

    }


