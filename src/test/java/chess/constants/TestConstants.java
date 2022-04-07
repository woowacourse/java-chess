package chess.constants;

import static org.junit.jupiter.params.ParameterizedTest.ARGUMENTS_PLACEHOLDER;
import static org.junit.jupiter.params.ParameterizedTest.DISPLAY_NAME_PLACEHOLDER;

public class TestConstants {

    public static final String PARAMETERIZED_TEST_NAME = DISPLAY_NAME_PLACEHOLDER + " [" + ARGUMENTS_PLACEHOLDER + "]";
    public static final String TEST_DB_URL = "jdbc:mysql://localhost:3306/chess_test";
}
