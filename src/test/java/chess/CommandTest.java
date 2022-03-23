package chess;

import java.util.regex.Pattern;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CommandTest {

    private static final String regex = "(start)|(end)|(move [a-h][1-8] [a-h][1-8])";
    public static final Pattern PATTERN = Pattern.compile(regex);

    @ParameterizedTest
    @ValueSource(strings = {"start", "end", "move a1 a2", "move a7 b6"})
    void regexp_pass(String input) {
        boolean matches = PATTERN.matcher(input).matches();
        Assertions.assertThat(matches).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"MOVE a7 b6", "startendstart", "sttart"})
    void regexp_exception(String input) {
        boolean matches = PATTERN.matcher(input).matches();
        Assertions.assertThat(matches).isFalse();
    }

}
