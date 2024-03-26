package chess.view.input;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GameCommandTest {

    @ParameterizedTest
    @ValueSource(strings = {"startb", "endd", "move"})
    @DisplayName("게임 시작 시 start 혹은 end를 입력하지 않으면 예외가 발생한다.")
    void createFirstGameCommand(String input) {
        // when & then
        assertThatThrownBy(() -> GameCommand.createInPreparation(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"movee", "start"})
    @DisplayName("게임 진행 중 move 혹은 end를 입력하지 않으면 예외가 발생한다.")
    void createMoveCommand(String input) {
        // when & then
        assertThatThrownBy(() -> GameCommand.createInProgress(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
