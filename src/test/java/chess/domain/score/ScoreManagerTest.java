package chess.domain.score;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.color.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.nonsliding.Knight;
import chess.domain.piece.pawn.WhiteFirstPawn;
import chess.domain.piece.pawn.WhitePawn;
import chess.domain.piece.sliding.Bishop;
import chess.domain.piece.sliding.Rook;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreManagerTest {

    @Test
    @DisplayName("전달 받은 말의 총 점수를 계산한다.")
    void calculateFileScore() {
        List<Piece> pieces = List.of(
                new Rook(Color.WHITE),
                new Knight(Color.WHITE),
                new Bishop(Color.WHITE),
                new WhiteFirstPawn()
        );
        ScoreManager scoreManager = new ScoreManager();

        assertThat(scoreManager.calculateFileScore(pieces)).isEqualTo(new Score(11.5));
    }

    @Test
    @DisplayName("전달 받은 피스들에 폰이 두 개 이상 있으면 한 개 당 0.5점으로 계산한다.")
    void calculateScoreHasOverTwoPawn() {
        List<Piece> pieces = List.of(
                new WhiteFirstPawn(),
                new WhitePawn(),
                new WhitePawn()
        );
        ScoreManager scoreManager = new ScoreManager();

        assertThat(scoreManager.calculateFileScore(pieces)).isEqualTo(new Score(1.5));
    }

    @Test
    @DisplayName("전달 받은 피스에 폰이 하나 있으면 1점으로 계산한다.")
    void calculateScoreHasOnePawn() {
        List<Piece> pieces = List.of(
                new WhitePawn()
        );
        ScoreManager scoreManager = new ScoreManager();

        assertThat(scoreManager.calculateFileScore(pieces)).isEqualTo(new Score(1));
    }
}
