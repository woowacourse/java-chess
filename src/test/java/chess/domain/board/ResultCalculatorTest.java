package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Map;

import chess.domain.piece.Side;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultCalculatorTest {

    @Test
    @DisplayName("진영별 게임 결과를 올바르게 저장한다. - 화이트 승")
    void saveGameResult_White_Win() {
        // given
        ResultCalculator resultCalculator = new ResultCalculator(new ScoreBySide(), new GameResultBySide());
        Score whiteTotalScore = new Score(new BigDecimal("20.0"));
        Score blackTotalScore = new Score(new BigDecimal("10.0"));

        // when
        resultCalculator.saveTotalScoreBySide(Side.WHITE, whiteTotalScore);
        resultCalculator.saveTotalScoreBySide(Side.BLACK, blackTotalScore);
        resultCalculator.saveGameResultBySide();
        Map<Side, GameResult> gameResultBySide = resultCalculator.getGameResultBySide();

        // then
        assertThat(gameResultBySide.get(Side.WHITE)).isEqualTo(GameResult.WIN);
        assertThat(gameResultBySide.get(Side.BLACK)).isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("진영별 게임 결과를 올바르게 저장한다. - 화이트 무")
    void saveGameResult_White_Draw() {
        // given
        ResultCalculator resultCalculator = new ResultCalculator(new ScoreBySide(), new GameResultBySide());
        Score whiteTotalScore = new Score(new BigDecimal("20.0"));
        Score blackTotalScore = new Score(new BigDecimal("20.0"));

        // when
        resultCalculator.saveTotalScoreBySide(Side.WHITE, whiteTotalScore);
        resultCalculator.saveTotalScoreBySide(Side.BLACK, blackTotalScore);
        resultCalculator.saveGameResultBySide();
        Map<Side, GameResult> gameResultBySide = resultCalculator.getGameResultBySide();

        // then
        assertThat(gameResultBySide.get(Side.WHITE)).isEqualTo(GameResult.DRAW);
        assertThat(gameResultBySide.get(Side.BLACK)).isEqualTo(GameResult.DRAW);
    }

    @Test
    @DisplayName("진영별 게임 결과를 올바르게 저장한다. - 화이트 패")
    void saveGameResult_White_Lose() {
        // given
        ResultCalculator resultCalculator = new ResultCalculator(new ScoreBySide(), new GameResultBySide());
        Score whiteTotalScore = new Score(new BigDecimal("10.0"));
        Score blackTotalScore = new Score(new BigDecimal("20.0"));

        // when
        resultCalculator.saveTotalScoreBySide(Side.WHITE, whiteTotalScore);
        resultCalculator.saveTotalScoreBySide(Side.BLACK, blackTotalScore);
        resultCalculator.saveGameResultBySide();
        Map<Side, GameResult> gameResultBySide = resultCalculator.getGameResultBySide();

        // then
        assertThat(gameResultBySide.get(Side.WHITE)).isEqualTo(GameResult.LOSE);
        assertThat(gameResultBySide.get(Side.BLACK)).isEqualTo(GameResult.WIN);
    }
}
