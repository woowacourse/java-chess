package chess.domain.move;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Board;
import chess.domain.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PawnMoveStrategyTest {

    @Test
    @DisplayName("폰 움직임 전략에 맞지 않는 경우 에러를 발생한다.")
    void isMovableException() {
        Board board = Board.create();
        Position source = Position.valueOf("a7");
        Position target = Position.valueOf("a8");

        PawnMoveStrategy pawnMoveStrategy = new PawnMoveStrategy();
        assertThatThrownBy(() -> pawnMoveStrategy.isMovable(board, source, target))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 이동할 수 없습니다.");
    }

    @Test
    @DisplayName("현재 위치에서 폰을 타 폰 위치로 움직일 수 있다.")
    void isMovable() {
        Board board = Board.create();
        Position source = Position.valueOf("a7");
        Position target = Position.valueOf("a6");

        PawnMoveStrategy pawnMoveStrategy = new PawnMoveStrategy();
        assertThat(pawnMoveStrategy.isMovable(board, source, target)).isTrue();
    }
}
