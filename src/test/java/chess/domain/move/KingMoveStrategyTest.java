package chess.domain.move;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KingMoveStrategyTest {

    @Test
    @DisplayName("")
    void isMovable() {
        Board board = Board.create();
        board.movePiece(Position.valueOf("e7"), Position.valueOf("e6"));

        Position source = Position.valueOf("e8");
        Position target = Position.valueOf("e7");

        KingMoveStrategy kingMoveStrategy = new KingMoveStrategy();
        assertThat(kingMoveStrategy.isMovable(board, source, target)).isTrue();
    }
}
