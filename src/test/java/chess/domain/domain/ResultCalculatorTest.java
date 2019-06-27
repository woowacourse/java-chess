package chess.domain.domain;

import chess.domain.Aliance;
import chess.domain.Board;
import chess.domain.ResultCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class ResultCalculatorTest {
    ResultCalculator resultCalculator;
    Board board;

    @BeforeEach
    void setUp() {
        board = new Board(Aliance.WHITE);
        resultCalculator = new ResultCalculator(board);
    }

    @Test
    void 보드초기화_점수확인() {
        assertThat(resultCalculator.calculateResult().get(Aliance.WHITE)).isEqualTo((double) 38);
        assertThat(resultCalculator.calculateResult().get(Aliance.BLACK)).isEqualTo((double) 38);
    }

    @Test
    void 폰겹칠때_점수확인() {
        board.movePiece("d2", "d4");
        board.switchTurn();
        board.movePiece("e7", "e5");
        board.switchTurn();
        board.movePiece("d4", "e5");
        assertThat(resultCalculator.calculateResult().get(Aliance.WHITE)).isEqualTo((double) 37);
    }
}
