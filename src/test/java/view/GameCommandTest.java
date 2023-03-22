package view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GameCommandTest {

    @DisplayName("게임 명령어 관련 유효하지 않은 입력이 들어오면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"star", "endd"})
    void fromValidationTest(String wrongCommand) {
        assertThatThrownBy(() -> GameCommand.from(wrongCommand))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 게임 커맨드입니다.");
    }

    @DisplayName("게임 명령어에 null이 들어올 경우 예외가 발생한다.")
    @Test
    void fromNullValidationTest() {
        assertThatThrownBy(() -> GameCommand.from(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 게임 커맨드입니다.");
    }

    @DisplayName("명령어 text에 맞는 GameCommand가 반환된다.")
    @ParameterizedTest
    @CsvSource(value = {"start,START", "end,END", "move,MOVE"})
    void gameCommandFromTest(String commandText, GameCommand command) {
        assertThat(GameCommand.from(commandText)).isEqualTo(command);
    }
}