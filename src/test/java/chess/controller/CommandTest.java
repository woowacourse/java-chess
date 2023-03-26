package chess.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Command 클래스")
public class CommandTest {
    @Nested
    @DisplayName("from 메서드는")
    class changeStatus {
        @Nested
        @DisplayName("사용자 입력으로")
        class given_code {
            @ParameterizedTest(name = "{0}가 주어지면 객체 {1}을 반환한다")
            @CsvSource({"start,START", "move,MOVE", "status,STATUS", "end,END"})
            void it_returns_command(String code, Command command) {
                assertThat(Command.from(code)).isEqualTo(command);
            }

            @ParameterizedTest(name = "{0}가 주어지면 예외를 던진다")
            @ValueSource(strings = {"sTart", "  ", "End", "stATus", "mov"})
            void it_throws_exception(String code) {
                assertThatThrownBy(() -> Command.from(code))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("잘못된 명령어입니다.");
            }
        }
    }

    @Nested
    @DisplayName("validateCommandInStart 메서드는")
    class validateCommandInStart {
        @ParameterizedTest(name = "{0} 상태에서 호출되면 예외를 반환하지 않는다")
        @ValueSource(strings = "START")
        void it_does_not_throws_exception(Command command) {
            Assertions.assertDoesNotThrow(command::validateCommandInStart);
        }

        @ParameterizedTest(name = "{0} 상태에서 호출되면 예외를 던진다")
        @ValueSource(strings = {"MOVE", "END"})
        void it_throws_exception(Command command) {
            assertThatThrownBy(command::validateCommandInStart)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("게임을 시작하기 위해서는 start를 입력하세요");
        }
    }

    @Nested
    @DisplayName("validateCommandInPlaying 메서드는")
    class validateCommandInPlaying {
        @Test
        @DisplayName("START에서 호출되면 예외를 던진다")
        void it_throws_exception() {
            assertThatThrownBy(Command.START::validateCommandInPlaying)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("게임 도중에는 다시 시작할 수 없습니다.");
        }

        @ParameterizedTest(name = "{0} 상태에서 호출되면 예외를 던지지 않는다")
        @ValueSource(strings = {"MOVE", "END"})
        void it_does_not_throw_exception(Command command) {
            Assertions.assertDoesNotThrow(command::validateCommandInPlaying);
        }
    }
}
