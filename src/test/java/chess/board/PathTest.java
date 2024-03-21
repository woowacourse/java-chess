package chess.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.piece.Color;
import chess.piece.Piece;
import chess.piece.Rook;
import chess.position.Position;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PathTest {

    @Test
    @DisplayName("Path 중간에 기물이 있는 경우를 판단한다.")
    void hasPieceOnRouteTest() {
        // given
        Position source = Position.of("a", 1);
        Position destination = Position.of("h", 8);
        Map<Position, Piece> pieces = Map.of(
                Position.of("d", 4), new Rook(Color.WHITE)
        );
        Path path = Path.createExcludingBothEnds(source, destination);
        // when, then
        assertThat(path.hasPieceOnRoute(pieces)).isTrue();
    }

    @Test
    @DisplayName("Path 중간에 기물이 없는 경우를 판단한다.")
    void hasNoPieceOnRouteTest() {
        // given
        Position source = Position.of("a", 1);
        Position destination = Position.of("h", 8);
        Map<Position, Piece> pieces = Map.of();
        Path path = Path.createExcludingBothEnds(source, destination);
        // when, then
        assertThat(path.hasPieceOnRoute(pieces)).isFalse();
    }
}
