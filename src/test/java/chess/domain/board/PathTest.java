package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PathTest {

    @Test
    @DisplayName("Path 중간에 기물이 있는 경우를 판단한다.")
    void hasPieceOnRouteTest() {
        // given
        Position source = Position.of(File.A, Rank.ONE);
        Position destination = Position.of(File.H, Rank.EIGHT);
        Map<Position, Piece> pieces = Map.of(
                Position.of(File.D, Rank.FOUR), new Rook(Color.WHITE)
        );
        Path path = Path.createExcludingBothEnds(source, destination);
        // when
        boolean hasPieceOnRoute = path.hasPieceOnRoute(pieces);
        // then
        assertThat(hasPieceOnRoute).isTrue();
    }

    @Test
    @DisplayName("Path 중간에 기물이 없는 경우를 판단한다.")
    void hasNoPieceOnRouteTest() {
        // given
        Position source = Position.of(File.A, Rank.ONE);
        Position destination = Position.of(File.H, Rank.EIGHT);
        Map<Position, Piece> pieces = Map.of();
        Path path = Path.createExcludingBothEnds(source, destination);
        // when
        boolean hasPieceOnRoute = path.hasPieceOnRoute(pieces);
        // then
        assertThat(hasPieceOnRoute).isFalse();
    }
}
