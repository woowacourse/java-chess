package chess.domain.move;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingMoveStrategyTest {

    private Board board;
    private KingMoveStrategy kingMoveStrategy;

    @BeforeEach
    void setUp() {
        board = new Board(BoardFactory.initialize());
        kingMoveStrategy = new KingMoveStrategy();
    }

    @Test
    @DisplayName("킹이 움직일 수 있다.")
    void isMovable() {
        board.movePiece(Position.valueOf("e7"), Position.valueOf("e6"));

        Position source = Position.valueOf("e8");
        Position target = Position.valueOf("e7");

        assertThat(kingMoveStrategy.isMovable(board, source, target)).isTrue();
    }

    @Test
    @DisplayName("킹의 이동 패턴이 아니다.")
    void isMovableNotKingMovePattern() {
        board.movePiece(Position.valueOf("e7"), Position.valueOf("e5"));

        Position source = Position.valueOf("e8");
        Position target = Position.valueOf("e6");

        assertThat(kingMoveStrategy.isMovable(board, source, target)).isFalse();
    }

    @Test
    @DisplayName("Target 에 우리편 기물이 있을 때 false")
    void isMovableWhenTargetTeamSame() {
        Position source = Position.valueOf("e8");
        Position target = Position.valueOf("e7");

        assertThat(kingMoveStrategy.isMovable(board, source, target)).isFalse();
    }

}
