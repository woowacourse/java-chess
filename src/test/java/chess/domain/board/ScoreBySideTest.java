package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Map;

import chess.domain.piece.Side;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreBySideTest {

    @Test
    @DisplayName("진영에 맞는 기물 점수를 업데이트한다.")
    void update() {
        // given
        ScoreBySide scoreBySide = new ScoreBySide();
        Score scoreToUpdate = new Score(new BigDecimal("9.0"));

        // when
        scoreBySide.updateScore(Side.WHITE, scoreToUpdate);
        Map<Side, Score> gotScoreBySide = scoreBySide.getScoreBySide();

        // then
        assertThat(gotScoreBySide.get(Side.WHITE)).isEqualTo(scoreToUpdate);
    }

}
