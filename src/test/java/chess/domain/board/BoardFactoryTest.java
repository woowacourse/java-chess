package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.Bishop;
import chess.domain.piece.Team;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardFactoryTest {

    @Test
    @DisplayName("최초의 Board 원시값을 생성한다.")
    void init() {
        Map<Position, Piece> expected = BoardFactory.initialize();
        List<Piece> pieces = getPieces(Team.BLACK);
        assertAll(
                () -> assertThat(expected.get(Position.valueOf("a8"))).isEqualTo(pieces.get(0)),
                () -> assertThat(expected.get(Position.valueOf("b8"))).isEqualTo(pieces.get(1)),
                () -> assertThat(expected.get(Position.valueOf("c8"))).isEqualTo(pieces.get(2)),
                () -> assertThat(expected.get(Position.valueOf("d8"))).isEqualTo(pieces.get(3)),
                () -> assertThat(expected.get(Position.valueOf("e8"))).isEqualTo(pieces.get(4)),
                () -> assertThat(expected.get(Position.valueOf("f8"))).isEqualTo(pieces.get(5)),
                () -> assertThat(expected.get(Position.valueOf("g8"))).isEqualTo(pieces.get(6)),
                () -> assertThat(expected.get(Position.valueOf("h8"))).isEqualTo(pieces.get(7))
        );
    }

    private List<Piece> getPieces(final Team team) {
        return List.of(
                new Rook(team),
                new Knight(team),
                new Bishop(team),
                new Queen(team),
                new King(team),
                new Bishop(team),
                new Knight(team),
                new Rook(team)
        );
    }

}