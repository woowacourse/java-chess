package chess.domain.move;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackPawnMoveStrategyTest {

    private Board board;
    private PawnMoveStrategy pawnMoveStrategy;

    @BeforeEach
    void setUp() {
        board = new Board(BoardFactory.initialize());
        pawnMoveStrategy = new BlackPawnMoveStrategy();
    }

    @Test
    @DisplayName("현재 위치에서 폰을 타 폰 위치로 움직일 수 있다.")
    void isMovable() {
        Position source = Position.valueOf("a7");
        Position target = Position.valueOf("a6");

        assertThat(pawnMoveStrategy.isMovable(board, source, target)).isTrue();
    }

    @Test
    @DisplayName("폰 움직임 전략에 맞지 않는 경우 false")
    void isMovableNotPawnMovePattern() {
        Position source = Position.valueOf("a7");
        Position target = Position.valueOf("a8");

        assertThat(pawnMoveStrategy.isMovable(board, source, target)).isFalse();
    }

    @Test
    @DisplayName("2칸 전진시 Pawn 이 사작위치가 아닌경우 false")
    void isMovableStartMoveNotStartPosition() {
        board.movePiece(Position.valueOf("a7"), Position.valueOf("a6"));
        Position source = Position.valueOf("a6");
        Position target = Position.valueOf("a4");

        assertThat(pawnMoveStrategy.isMovable(board, source, target)).isFalse();
    }

    @Test
    @DisplayName("2칸 전진시 기물이 있을 경우 false")
    void isMovableStartMoveHasPieceOnTarget() {
        board.movePiece(Position.valueOf("a2"), Position.valueOf("a5"));
        Position source = Position.valueOf("a7");
        Position target = Position.valueOf("a5");

        assertThat(pawnMoveStrategy.isMovable(board, source, target)).isFalse();
    }

    @Test
    @DisplayName("전진시 기물이 있을 경우 false")
    void isMovableSouthHasPieceOnTarget() {
        board.movePiece(Position.valueOf("a2"), Position.valueOf("a6"));
        Position source = Position.valueOf("a7");
        Position target = Position.valueOf("a6");

        assertThat(pawnMoveStrategy.isMovable(board, source, target)).isFalse();
    }

    @Test
    @DisplayName("대각선 이동시 기물이 있을 경우 true")
    void isMovableSouthEastHasPieceOnTarget() {
        board.movePiece(Position.valueOf("a2"), Position.valueOf("b6"));
        Position source = Position.valueOf("a7");
        Position target = Position.valueOf("b6");

        assertThat(pawnMoveStrategy.isMovable(board, source, target)).isTrue();
    }
}
