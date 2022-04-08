package chess.domain.move;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KnightMoveStrategyTest {

    private Board board;
    private KnightMoveStrategy knightMoveStrategy;

    @BeforeEach
    void setUp() {
        board = BoardFactory.createInitChessBoard();
        knightMoveStrategy = new KnightMoveStrategy();
    }

    @Test
    @DisplayName("나이트가 움직일 수 있다.")
    void isMovable() {
        Position source = Position.of('b', 8);
        Position target = Position.of('c', 6);

        assertThat(knightMoveStrategy.isMovable(board, source, target)).isTrue();
    }

    @Test
    @DisplayName("나이트 이동 패턴이 아니다.")
    void isMovableNotKnightMovePattern() {
        Position source = Position.of('b', 8);
        Position target = Position.of('c', 7);

        assertThat(knightMoveStrategy.isMovable(board, source, target)).isFalse();
    }

    @Test
    @DisplayName("Target 에 우리편 기물이 있을 때 false")
    void isMovableWhenTargetColorSame() {
        board.movePiece(Position.of('b', 7), Position.of('c', 6));

        Position source = Position.of('b', 8);
        Position target = Position.of('c', 6);

        assertThat(knightMoveStrategy.isMovable(board, source, target)).isFalse();
    }
}
