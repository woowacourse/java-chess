package chess.domain.game;

import chess.domain.piece.Color;
import chess.domain.piece.Score;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StatusTest {
    
    @Test
    @DisplayName("Status 객체를 생성하고 점수를 반환한다.")
    void createStatus() {
        Map<Color, Score> scoreMap = Map.of(Color.WHITE, Score.from(1.0), Color.BLACK, Score.from(2.0));
        Status status = Status.from(scoreMap);
        Assertions.assertThat(status.getScore(Color.WHITE)).isEqualTo(Score.from(1.0));
        Assertions.assertThat(status.getScore(Color.BLACK)).isEqualTo(Score.from(2.0));
    }
    
    @Test
    @DisplayName("두 팀의 점수 차이를 반환한다.")
    void getScoreDifference() {
        Map<Color, Score> scoreMap = Map.of(Color.WHITE, Score.from(1.0), Color.BLACK, Score.from(2.0));
        Status status = Status.from(scoreMap);
        Assertions.assertThat(status.getScoreDifference()).isEqualTo(-1.0);
    }
    
    @Test
    @DisplayName("승리한 팀을 반환한다.")
    void getWinner() {
        Map<Color, Score> scoreMap = Map.of(Color.WHITE, Score.from(1.0), Color.BLACK, Score.from(2.0));
        Status status = Status.from(scoreMap);
        Assertions.assertThat(status.getWinner()).isEqualTo(Color.BLACK);
    }
}