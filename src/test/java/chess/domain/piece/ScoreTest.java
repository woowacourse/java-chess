package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {
    
    @Test
    @DisplayName("Score 객체를 생성한다.")
    void createScore() {
        Score score = Score.from(1.0);
        assertEquals(score, Score.from(1.0));
    }
    
    @Test
    @DisplayName("Score 객체를 더한다.")
    void addScore() {
        Score score = Score.from(1.0);
        Score score2 = Score.from(2.0);
        assertEquals(score.add(score2), Score.from(3.0));
    }
    
}