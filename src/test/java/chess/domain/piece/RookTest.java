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

@DisplayName("룩")
class RookTest {

    @ParameterizedTest
    @ValueSource(strings = {"a6", "h6", "c1", "c8"})
    @DisplayName("에 대한 이동 루트가 상하좌우로 이동하는지 판단한다.")
    void canMove(String target) {
        Board board = new Board(Map.of());
        Rook rook = new Rook(PieceColor.BLACK);

        boolean actual = rook.canMove(Position.from("c6"), Position.from(target), board);

        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("목적지로 향하는 경로에 다른 기물이 존재하면 이동할 수 없다.")
    void cannotMove() {
        Board board = new Board(Map.of(Position.from("h5"), new Rook(PieceColor.BLACK)));
        Rook rook = new Rook(PieceColor.BLACK);

        boolean actual = rook.canMove(Position.from("h6"), Position.from("h1"), board);

        assertThat(actual).isFalse();
    }
}
