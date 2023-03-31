package chess.domain.game;

import chess.domain.piece.PieceType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PieceScoreTest {

    @Test
    @DisplayName("기물의 점수는 점수 규칙에 맞게 계산된다.")
    void getScore() {
        double kingScore = PieceScore.getScore(PieceType.KING);
        double queenScore = PieceScore.getScore(PieceType.QUEEN);
        double knightScore = PieceScore.getScore(PieceType.KNIGHT);
        double rookScore = PieceScore.getScore(PieceType.ROOK);
        double bishopScore = PieceScore.getScore(PieceType.BISHOP);
        double pawnScore = PieceScore.getScore(PieceType.PAWN);

        assertAll(
                () -> assertEquals(0d, kingScore),
                () -> assertEquals(9d, queenScore),
                () -> assertEquals(2.5d, knightScore),
                () -> assertEquals(5d, rookScore),
                () -> assertEquals(3d, bishopScore),
                () -> assertEquals(1d, pawnScore)
        );
    }
}
