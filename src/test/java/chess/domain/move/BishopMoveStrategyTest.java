package chess.domain.move;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BishopMoveStrategyTest {

    Board board;
    BishopMoveStrategy bishopMoveStrategy;

    @BeforeEach
    void setUp() {
        board = Board.create();
        bishopMoveStrategy = new BishopMoveStrategy();
    }

    @Test
    @DisplayName("비숍이 이동할 수 있다.")
    void isMovable() {
        board.movePiece(Position.valueOf("d7"), Position.valueOf("d6"));

        Position source = Position.valueOf("c8");
        Position target = Position.valueOf("e6");

        assertThat(bishopMoveStrategy.isMovable(board, source, target)).isTrue();
    }
}
