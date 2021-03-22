package chess.domain;

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
        Score expectedWhiteScore = chessResult.whiteTeamScore();
        Score expectedBlackScore = chessResult.blackTeamScore();

        //than
        assertThat(expectedWhiteScore).isEqualTo(whiteScore);
        assertThat(expectedBlackScore).isEqualTo(blackScore);
    }

    @Test
    @DisplayName("최종점수 비교를 통해 winner팀 반환 테스트")
    void testWinner() {
        //when
        TeamColor winner = chessResult.winner();

        //than
        assertThat(winner).isEqualTo(TeamColor.WHITE);
    }
}
