package chess.domain;

import chess.domain.game.ChessResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessResultTest {

    Score whiteScore;
    Score blackScore;
    ChessResult chessResult;

    @BeforeEach
    void setUp() {
        whiteScore = Score.from(10);
        blackScore = Score.from(8);
        chessResult = new ChessResult(whiteScore, blackScore);
    }

    @Test
    @DisplayName("각 팀별 최종점수 확인 테스트")
    void testTeamScore() {
        //when
        double expectedWhiteScore = chessResult.getWhiteTeamScore();
        double expectedBlackScore = chessResult.getBlackTeamScore();

        //than
        assertThat(expectedWhiteScore).isEqualTo(whiteScore.value());
        assertThat(expectedBlackScore).isEqualTo(blackScore.value());
    }
}
