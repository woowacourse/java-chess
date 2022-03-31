package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.Color;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {
    @Test
    @DisplayName("초기화된 체스판의 점수 계산 테스트")
    void calculateInitialBoardScore() {
        final Board board = BoardFactory.newInstance();
        final Map<Color, Double> score = board.getScore();
        
        assertAll(
                () -> assertThat(score.get(Color.WHITE)).isEqualTo(38.0),
                () -> assertThat(score.get(Color.BLACK)).isEqualTo(38.0)
        );
    }
}
