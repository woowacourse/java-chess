package chess.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Command 클래스")
public class CommandTest {

    @Nested
    @DisplayName("from 메서드는")
    class from {
        @Nested
        @DisplayName("사용자 입력으로")
        class given_code {
            @ParameterizedTest(name = "{0}가 주어지면 객체 {1}을 반환한다")
            @CsvSource({"start,START", "end,END"})
            void it_returns_command(String code, Command command) {
                assertThat(Command.from(code)).isEqualTo(command);
            }
        }
    }
}