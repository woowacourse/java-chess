package view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GameCommandTest {

    @DisplayName("게임 명령어 관련 유효하지 않은 입력이 들어오면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"star", "endd", ""})
    void fromValidationTest(String wrongCommand) {
        List<String> commands = Arrays.asList(wrongCommand.split(" "));
        assertThatThrownBy(() -> GameCommand.from(commands))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 게임 커맨드입니다.");
    }

    @DisplayName("게임 명령어에 null이 들어올 경우 예외가 발생한다.")
    @Test
    void fromNullValidationTest() {
        assertThatThrownBy(() -> GameCommand.from(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("아무 값도 입력되지 않았습니다.");
    }

    @DisplayName("명령어 text에 맞는 GameCommand가 반환된다.")
    @ParameterizedTest
    @CsvSource(value = {"start,START", "end,END", "move b2 b4,MOVE"})
    void gameCommandFromTest(String commandText, GameCommand command) {
        List<String> commands = Arrays.asList(commandText.split(" "));
        assertThat(GameCommand.from(commands)).isEqualTo(command);
    }
}