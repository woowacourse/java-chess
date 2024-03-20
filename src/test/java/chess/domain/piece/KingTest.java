package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Board;
import chess.domain.PieceColor;
import chess.domain.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;

@DisplayName("킹")
class KingTest {

    @ParameterizedTest
    @ValueSource(strings = {"b7", "c7", "d7", "d6", "d5", "c5", "b5", "b6"})
    @DisplayName("에 대한 이동 루트가 상하좌우 대각선 중 하나인지 판단한다.")
    void canMove(String target) {
        Board board = new Board(Map.of());
        King king = new King(PieceColor.BLACK);

        boolean actual = king.canMove(Position.from("c6"), Position.from(target), board);

        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("목적지로 향하는 경로에 다른 기물이 존재하면 이동할 수 없다.")
    void cannotMove() {
        Board board = new Board(Map.of(Position.from("d5"), new Rook(PieceColor.BLACK)));
        King king = new King(PieceColor.BLACK);

        boolean actual = king.canMove(Position.from("c6"), Position.from("d5"), board);

        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("1칸 초과하여 이동 할 수 없다.")
    void cannotMoveMoreThanTwoStep() {
        Board board = new Board(Map.of());
        King king = new King(PieceColor.BLACK);

        boolean actual = king.canMove(Position.from("c6"), Position.from("c4"), board);

        assertThat(actual).isFalse();
    }
}
