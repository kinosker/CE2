package unitTesting;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestCommandAdd.class, TestCommandClear.class, TestCommandDelete.class, TestCommandSearch.class, TestStringHandler.class })
public class TestCases {

}
