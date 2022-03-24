package chess.domain.move;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KnightMoveStrategyTest {

    Board board;
    KnightMoveStrategy knightMoveStrategy;

    @BeforeEach
    void setUp() {
        board = Board.create();
        knightMoveStrategy = new KnightMoveStrategy();
    }

    @Test
    @DisplayName("나이트가 움직일 수 있다.")
    void isMovable() {
        Position source = Position.valueOf("b8");
        Position target = Position.valueOf("c6");

        assertThat(knightMoveStrategy.isMovable(board, source, target)).isTrue();
    }
}
