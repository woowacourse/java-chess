package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {
    @Test
    @DisplayName("기물 별 점수 환산 테스트")
    void calculateScoreByPiece() {
        // given
        double queenScore = Score.from(new Queen(Color.WHITE));
        double rookScore = Score.from(new Rook(Color.WHITE));
        double bishopScore = Score.from(new Bishop(Color.WHITE));
        double knightScore = Score.from(new Knight(Color.WHITE));
        double pawnScore = Score.from(new Pawn(Color.WHITE));
        double kingScore = Score.from(new King(Color.WHITE));

        // when & then
        assertAll(
                () -> assertThat(queenScore).isEqualTo(9),
                () -> assertThat(rookScore).isEqualTo(5),
                () -> assertThat(bishopScore).isEqualTo(3),
                () -> assertThat(knightScore).isEqualTo(2.5),
                () -> assertThat(pawnScore).isEqualTo(1),
                () -> assertThat(kingScore).isEqualTo(0)
        );
    }
}
