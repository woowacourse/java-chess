package chess.domain;

import chess.domain.piece.PieceFactory;
import chess.domain.piece.Pieces;
import chess.domain.piece.info.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreStatusTest {
    @DisplayName("색깔별 기물 상태 객체를 반환한다.")
    @Test
    void 색깔별_기물_상태_생성() {
        Pieces pieces = new Pieces(PieceFactory.initialPieces());
        ScoreStatus scoreStatus = ScoreStatus.generateByColor(pieces);

        double blackScore = scoreStatus.totalScoreByColor().get(Color.BLACK);
        double whiteScore = scoreStatus.totalScoreByColor().get(Color.WHITE);

        assertThat(blackScore).isEqualTo(38.0);
        assertThat(whiteScore).isEqualTo(38.0);
    }
}
