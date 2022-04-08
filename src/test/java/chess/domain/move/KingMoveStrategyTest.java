package chess.domain.move;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KingMoveStrategyTest {

    private Board board;
    private KingMoveStrategy kingMoveStrategy;

    @BeforeEach
    void setUp() {
        board = BoardFactory.createInitChessBoard();
        kingMoveStrategy = new KingMoveStrategy();
    }

    @Test
    @DisplayName("킹이 이동 할 수 있다.")
    void isMovable() {
        board.movePiece(Position.of('e', 7), Position.of('e', 6));

        Position source = Position.of('e', 8);
        Position target = Position.of('e', 7);

        assertThat(kingMoveStrategy.isMovable(board, source, target)).isTrue();
    }

    @Test
    @DisplayName("킹의 이동 패턴이 아니다.")
    void isMovableNotKingMovePattern() {
        board.movePiece(Position.of('e', 7), Position.of('e', 5));

        Position source = Position.of('e', 8);
        Position target = Position.of('e', 6);

        assertThat(kingMoveStrategy.isMovable(board, source, target)).isFalse();
    }

    @Test
    @DisplayName("Target 에 우리편 기물이 있을 때 false")
    void isMovableWhenTargetColorSame() {
        Position source = Position.of('e', 8);
        Position target = Position.of('e', 7);

        assertThat(kingMoveStrategy.isMovable(board, source, target)).isFalse();
    }
}
