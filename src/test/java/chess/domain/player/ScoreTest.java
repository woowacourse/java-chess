package chess.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Position;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {

    @Test
    @DisplayName("모든 체스말의 총 점수를 구한다.")
    void calculateScore() {
        final List<Piece> pieces = List.of(
                new Pawn(new Position(2, 'a')),
                new King(new Position(4, 'b')),
                new Knight(new Position(5, 'a')),
                new Bishop(new Position(6, 'a')),
                new Queen(new Position(6, 'c')),
                new Rook(new Position(7, 'a'))
        );
        final double expected = 1 + 0 + 2.5 + 3 + 9 + 5;

        final Score score = new Score(pieces);
        final double actual = score.getScore();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("같은 세로줄에 폰이 있는 경우, 0.5점으로 계산한다.")
    void calculatePawnSameFile() {
        final List<Piece> pieces = List.of(
                new Pawn(new Position(2, 'a')),
                new Pawn(new Position(3, 'a')),
                new Pawn(new Position(4, 'a'))
        );
        final double expected = 0.5 * 3;

        final Score score = new Score(pieces);
        final double actual = score.getScore();

        assertThat(actual).isEqualTo(expected);
    }
}
