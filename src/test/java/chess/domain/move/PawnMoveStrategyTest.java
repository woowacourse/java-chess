package chess.domain.move;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PawnMoveStrategyTest {

    @Test
    @DisplayName("현재 위치에서 폰을 타폰 위치로 움직일 수 있다.")
    void isMovable() {
        Board board = Board.create();
        Position source = Position.valueOf("a7");
        Position target = Position.valueOf("a6");

        PawnMoveStrategy pawnMoveStrategy = new PawnMoveStrategy();
        assertThat(pawnMoveStrategy.isMovable(board, source, target)).isTrue();
    }
}
