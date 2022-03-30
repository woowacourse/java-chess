package chess.domain.board;

import chess.domain.piece.*;
import chess.domain.piece.notation.Color;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardCalculatorTest {

    @DisplayName("보드판에서 합계 구하기 폰이 같은 file 에 있는 경우 0.5 점으로 계산")
    @ParameterizedTest
    @ValueSource(strings = {"WHITE", "BLACK"})
    void sumScore(final Color color) {
        final Board board = new Board(() -> new HashMap<>(Map.of(
                Position.from("e3"), new Pawn(color),
                Position.from("e4"), new Pawn(color),
                Position.from("b5"), new Pawn(color),
                Position.from("a1"), new Queen(color),
                Position.from("a2"), new Rook(color),
                Position.from("a3"), new Bishop(color),
                Position.from("a4"), new Knight(color)
        )));

        assertThat(new BoardCalculator(board.getValue()).sumScore(color)).isEqualTo(Map.of(color, 21.5));
    }

    @DisplayName("보드판에서 합계 구하기")
    @ParameterizedTest
    @ValueSource(strings = {"WHITE", "BLACK"})
    void sumScore2(final Color color) {
        final Board board = new Board(() -> new HashMap<>(Map.of(
                Position.from("e4"), new Pawn(color),
                Position.from("b5"), new Pawn(color),
                Position.from("a1"), new Queen(color),
                Position.from("a2"), new Rook(color),
                Position.from("a3"), new Bishop(color),
                Position.from("a4"), new Knight(color)
        )));

        assertThat(new BoardCalculator(board.getValue()).sumScore(color)).isEqualTo(Map.of(color, 21.5));
    }
}
