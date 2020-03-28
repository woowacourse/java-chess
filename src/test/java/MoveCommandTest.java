import chess.domain.MoveCommand;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MoveCommandTest {

    @ParameterizedTest
    @DisplayName("잘못된 명령어가 입력되면 예외가 발생해야 함")
    @ValueSource(strings = {"움직여", "gogo", "MOVE", "ㄱ"})
    void wrongCommandWordInputThenThrowException(String input) {
        Assertions.assertThatThrownBy(() -> new MoveCommand(input)).hasMessage("잘못된 명령어가 입력되었습니다.");
    }

    @ParameterizedTest
    @DisplayName("잘못된 형태의 위치가 입력되면 예외가 발생해야 함")
    @ValueSource(strings = {"move A8 b3", "move dd d1", "move h8 13", "move 3a 5c", "move i-7 2-h", "move h~2 g5",
            "move a1 e11"})
    void wrongCharacterInputThenThrowException(String input) {
        Assertions.assertThatThrownBy(() -> new MoveCommand(input)).hasMessage("잘못된 형태의 위치가 입력되었습니다.");
    }
}
