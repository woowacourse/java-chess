package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Board;
import chess.domain.PieceColor;
import chess.domain.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;

@DisplayName("룩")
class RookTest {

    @ParameterizedTest
    @ValueSource(strings = {"a6", "h6", "c1", "c8"})
    @DisplayName("에 대한 이동 루트가 상하 또는 좌우로 이동하는지 판단한다.")
    void canMove(String target) {
        Board board = new Board(Map.of());
        Rook rook = new Rook(PieceColor.BLACK);

        boolean actual = rook.canMove(Position.from("c6"), Position.from(target), board);

        assertThat(actual).isTrue();
    }
}
