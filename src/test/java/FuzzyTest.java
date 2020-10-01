import com.bos.training.CsvComparator;
import edu.berkeley.cs.jqf.fuzz.Fuzz;
import edu.berkeley.cs.jqf.fuzz.JQF;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.util.ArrayList;

@RunWith(JQF.class)
public class FuzzyTest {

    private static CsvComparator csvComparator;

    @BeforeClass
    public static void beforeClass(){
        csvComparator = new CsvComparator();
    }

    @Fuzz
    public void validateUserInput(ArrayList<String> input){
        try {
            csvComparator.validateUserInput(input);
        } catch(Throwable e){
            System.out.println("Invalid input: \""+input+"\"");
        }
    }
}

