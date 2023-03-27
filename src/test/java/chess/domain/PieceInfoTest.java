package chess.domain;

import chess.domain.chesspiece.PieceInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("PieceInfo 클래스")
public class PieceInfoTest {
    @Nested
    @DisplayName("addScore 메서드는")
    class calculatePiecesScore {
        final int score = 0;
        @ParameterizedTest(name = "0이 주어지면 {0}의 점수를 더한 {1}을 반환한다.")
        @CsvSource(value = {"PAWN,1", "ROOK,5", "BISHOP,3", "KNIGHT,2.5", "QUEEN,9", "KING,0"})
        void it_returns_increased_score(final PieceInfo pieceInfo, final double increasedScore) {
            assertThat(pieceInfo.addScore(score)).isEqualTo(increasedScore);
        }
    }
}
