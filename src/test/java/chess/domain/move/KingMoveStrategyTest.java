package chess.domain.move;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KingMoveStrategyTest {

    Board board;
    KingMoveStrategy kingMoveStrategy;

    @BeforeEach
    void setUp() {
        board = Board.create();
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
}
