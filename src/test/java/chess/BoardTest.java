package chess;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BoardTest {

    @ParameterizedTest(name = "moveCommand : {0}")
    @ValueSource(strings = {"a3 a4", "g3 g4", "a6 a5", "g6 g5", "d3 d4", "d6 e5"})
    @DisplayName("현재 위치에 말이 존재하는지 검증한다.")
    void existPieceInFromPosition(final String actual) {
        final Board board = Board.create();

        assertThatThrownBy(() -> board.move(MoveCommand.of(actual)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치에 말이 존재하지 않습니다.");
    }

    @ParameterizedTest(name = "moveCommand : {0}")
    @ValueSource(strings = {"a1 a2", "g1 g2", "a7 a8", "g7 g8", "d2 d1", "d7 d8"})
    @DisplayName("이동할 위치에 말이 존재하는지 검증한다.")
    void existPieceInToPosition(final String actual) {
        final Board board = Board.create();

        assertThatThrownBy(() -> board.move(MoveCommand.of(actual)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동할 위치에 같은색의 말이 존재합니다.");
    }

}
