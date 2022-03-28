package chess.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.piece.Team;
import org.junit.jupiter.api.Test;

public class StatusCalculatorTest {
    @Test
    void calculate() {
        StatusCalculator scoreCalculator = new StatusCalculator(Board.create().getValue());
        assertThat(scoreCalculator.calculate(Team.WHITE)).isEqualTo(38);
    }
}
