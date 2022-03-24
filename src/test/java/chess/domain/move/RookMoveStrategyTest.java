package chess.domain.move;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RookMoveStrategyTest {

    Board board;
    RookMoveStrategy rookMoveStrategy;

    @BeforeEach
    void setUp() {
        board = Board.create();
        rookMoveStrategy = new RookMoveStrategy();
    }

    @Test
    @DisplayName("룩이 이동할 수 있다.")
    void isMovable() {
        board.movePiece(Position.valueOf("a7"), Position.valueOf("b6"));

        Position source = Position.valueOf("a8");
        Position target = Position.valueOf("a4");

        assertThat(rookMoveStrategy.isMovable(board, source, target)).isTrue();
    }
}
