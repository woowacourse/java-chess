package chess.domain.game.result;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Camp;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameResultTest {

    @Test
    @DisplayName("게임 결과에 판정 점수가 존재하는 지 확인 할 수 있다. -> true")
    void containsScoreTest() {
        Map<Camp, Double> scoreByCamp = Map.of(
                Camp.WHITE, 20d,
                Camp.BLACK, 10d
        );

        GameResult gameResult = new GameResult(MatchResult.WHITE_WIN, scoreByCamp);

        assertThat(gameResult.containsScore()).isTrue();
    }

    @Test
    @DisplayName("게임 결과에 판정 점수가 존재하는 지 확인 할 수 있다. -> false")
    void containsScoreTestWithoutScore() {
        GameResult gameResult = new GameResult(MatchResult.WHITE_WIN);

        assertThat(gameResult.containsScore()).isFalse();
    }

    @Test
    @DisplayName("해당 캠프의 게임 점수를 구할 수 있다.")
    void peekScoreOfCampTest() {
        double whiteCampScore = 20d;
        Map<Camp, Double> scoreByCamp = Map.of(
                Camp.WHITE, whiteCampScore,
                Camp.BLACK, 10d
        );

        GameResult gameResult = new GameResult(MatchResult.WHITE_WIN, scoreByCamp);

        assertThat(gameResult.peekScoreOfCamp(Camp.WHITE)).isEqualTo(whiteCampScore);
    }
}
