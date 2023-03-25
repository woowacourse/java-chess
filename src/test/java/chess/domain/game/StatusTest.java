package chess.domain.game;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static chess.domain.piece.PieceType.ROOK;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StatusTest {

    private Status status;

    @BeforeEach
    void setUp() {
        Map<Position, Piece> pieces = Map.of(
                new Position("c2"), new Pawn(WHITE),
                new Position("d2"), new Pawn(WHITE),
                new Position("f2"), new Piece(WHITE, ROOK),
                new Position("e2"), new Pawn(BLACK)
        );
        status = Status.from(pieces);
    }

    @DisplayName("색깔별 점수를 반환한다")
    @Test
    void getScore() {
        assertThat(status.getScore(WHITE)).isEqualTo(Score.valueOf(7));
        assertThat(status.getScore(BLACK)).isEqualTo(Score.valueOf(1));
    }

    @DisplayName("승자를 반환한다")
    @Test
    void getWinner() {
        assertThat(status.getWinner()).isSameAs(WHITE);
    }
}
