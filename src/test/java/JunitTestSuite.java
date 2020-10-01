import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        AccountTypeValidationTest.class,AutoSortTest.class, CheckFileCreatedTest.class,CompareBalanceTest.class, CurrencyValidationTest.class,FileNotFoundTest.class, FuzzyTest.class,InvalidAccountNumberTest.class, InvalidColumnsTest.class, InvalidFieldIntType.class, InvalidFileTypeTest.class, InvalidUserInputTest.class
})

public class JunitTestSuite {
}
