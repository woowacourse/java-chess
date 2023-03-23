package domain.chessboard;

import domain.piece.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static domain.piece.Color.BLACK;
import static domain.piece.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

public class StatusResultTest {

    @Test
    @DisplayName("White 가 이기는 경우")
    void whiteWin() {
        // Given
        ColorScore blackScore = new ColorScore(Color.BLACK, 30);
        ColorScore whiteScore = new ColorScore(WHITE, 31);
        StatusResult statusResult = StatusResult.of(blackScore, whiteScore);

        // When
        Map<Color, Double> score = statusResult.getScore().getValue();
        Map<Color, GameResult> result = statusResult.getResult().getValue();

        // Then
        assertThat(score.get(WHITE)).isEqualTo(31);
        assertThat(score.get(BLACK)).isEqualTo(30);
        assertThat(result.get(WHITE)).isEqualTo(GameResult.WIN);
        assertThat(result.get(BLACK)).isEqualTo(GameResult.LOSE);
    }

}
