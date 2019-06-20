package chess.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreCalculatorTest {
    @Test
    public void name() {
        Board board = new Board(BoardGenerator.generate());
        ScoreCalculator scoreCalculator = board.createScoreCalculator();

        assertThat(scoreCalculator.getWhiteScore()).isEqualTo(scoreCalculator.getBlackScore());
    }
}
