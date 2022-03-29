package chess.domain.board;

import chess.domain.Team;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CalculatorTest {

    @Test
    void calculateScore() {
        Board board = new Board();
        assertThat(board.calculateScore(Team.BLACK)).isEqualTo(38);
    }

    @Test
    void calculateScoreWithSameColumnPawn() {
        Board board = new Board();

        Position whitePawn = Position.of(2, 4);
        Position whitePawnTarget = Position.of(4, 4);
        board.move(whitePawn, whitePawnTarget);

        Position blackPawn = Position.of(7, 5);
        Position blackPawnTarget = Position.of(5, 5);
        board.move(blackPawn, blackPawnTarget);

        board.move(whitePawnTarget, blackPawnTarget);

        assertThat(board.calculateScore(Team.WHITE)).isEqualTo(37);
    }
}
