package chess.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ChessStatusTest {

    @Test
    @DisplayName("입력에 따라 체스의 상태가 결정됩니다. - 시작")
    void fromUserInputRunTest() {
        String userInput = "start";

        ChessStatus chessStatus = ChessStatus.from(userInput);

        assertThat(chessStatus).isEqualTo(ChessStatus.RUN);
    }

    @Test
    @DisplayName("입력에 따라 체스의 상태가 결정됩니다. - 종료")
    void fromUserInputEndTest() {
        String userInput = "end";

        ChessStatus chessStatus = ChessStatus.from(userInput);

        assertThat(chessStatus).isEqualTo(ChessStatus.END);
    }

    @ParameterizedTest(name = "유효하지 않은 입력인 경우 예외가 발생합니다.")
    @ValueSource(strings = {"Start", "END", "ada", "Hello"})
    void fromUserInputExceptionTest(String userInput) {

        assertThatThrownBy(() -> ChessStatus.from(userInput))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
