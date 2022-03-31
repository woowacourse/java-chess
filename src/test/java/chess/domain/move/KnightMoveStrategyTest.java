package chess.domain.move;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.CatchPieces;
import chess.domain.board.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KnightMoveStrategyTest {

    private Board board;
    private KnightMoveStrategy knightMoveStrategy;
    private CatchPieces catchPieces;

    @BeforeEach
    void setUp() {
        board = BoardFactory.createChessBoard();
        knightMoveStrategy = new KnightMoveStrategy();
        catchPieces = new CatchPieces();
    }

    @Test
    @DisplayName("나이트가 움직일 수 있다.")
    void isMovable() {
        Position source = Position.valueOf("b8");
        Position target = Position.valueOf("c6");

        assertThat(knightMoveStrategy.isMovable(board, source, target)).isTrue();
    }

    @Test
    @DisplayName("나이트 이동 패턴이 아니다.")
    void isMovableNotKnightMovePattern() {
        Position source = Position.valueOf("b8");
        Position target = Position.valueOf("c7");

        assertThat(knightMoveStrategy.isMovable(board, source, target)).isFalse();
    }

    @Test
    @DisplayName("Target 에 우리편 기물이 있을 때 false")
    void isMovableWhenTargetColorSame() {
        board.movePiece(Position.valueOf("b7"), Position.valueOf("c6"), catchPieces);

        Position source = Position.valueOf("b8");
        Position target = Position.valueOf("c6");

        assertThat(knightMoveStrategy.isMovable(board, source, target)).isFalse();
    }
}
