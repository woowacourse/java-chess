package chess.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class GameCommandTest {

    @ParameterizedTest
    @ValueSource(strings = {"glan", "glen fiddich", "glen fiddich livet"})
    @DisplayName("잘못된 입력이 들어오면 예외가 발생해야 한다.")
    void create_Fail(String input) {
        // expect
        assertThatThrownBy(() -> GameCommand.of(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 해당 커맨드가 존재하지 않습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n"})
    @DisplayName("빈 입력이 들어오면 예외가 발생해야 한다.")
    void create_Blank(String input) {
        // expect
        assertThatThrownBy(() -> GameCommand.of(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 공백은 입력될 수 없습니다.");
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("빈 입력이 들어오면 예외가 발생해야 한다.")
    void create_NullInput(String input) {
        // expect
        assertThatThrownBy(() -> GameCommand.of(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 입력은 null 값이 될 수 없습니다.");
    }

    @Test
    @DisplayName("긴 입력이 들어오면 예외가 발생해야 한다.")
    void create_OverSize() {
        // expect
        assertThatThrownBy(() -> GameCommand.of("123456789012345678901234567890123456789"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 입력의 최대 길이를 초과했습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"start", "end"})
    @DisplayName("command가 정확하게 반환되어야 한다.")
    void getCommand_Success(String input) {
        // given
        GameCommand gameCommand = GameCommand.of(input);

        // when
        Command command = gameCommand.getCommand();

        // then
        assertThat(command)
                .isEqualTo(Command.valueOf(input.toUpperCase()));
    }

    @Test
    @DisplayName("parameters가 정확하게 반환되어야 한다.")
    void getParameter_Success() {
        // given
        GameCommand gameCommand = GameCommand.of("move a1 a2");

        // when
        String firstParameter = gameCommand.getParameter(0);
        String secondParameter = gameCommand.getParameter(1);

        // then
        assertThat(firstParameter)
                .isEqualTo("a1");
        assertThat(secondParameter)
                .isEqualTo("a2");
    }

    @Test
    @DisplayName("잘못된 parameter를 찾으면 예외가 발생한다.")
    void getParameter_WrongIndex() {
        // given
        GameCommand gameCommand = GameCommand.of("move a1 a2");

        // expect
        assertThatThrownBy(() -> gameCommand.getParameter(3))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 잘못된 파라미터 인덱스 입니다.");
    }
}
