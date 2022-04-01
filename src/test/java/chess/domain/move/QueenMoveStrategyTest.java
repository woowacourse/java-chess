package chess.domain.move;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueenMoveStrategyTest {

    private Board board;
    private QueenMoveStrategy queenMoveStrategy;

    @BeforeEach
    void setUp() {
        board = new Board(BoardFactory.initialize());
        queenMoveStrategy = new QueenMoveStrategy();
    }

    @Test
    @DisplayName("퀸이 대각 이동할 수 있다.")
    void isMovableDiagonal() {
        board.movePiece(Position.valueOf("c7"), Position.valueOf("c6"));

        Position source = Position.valueOf("d8");
        Position target = Position.valueOf("c7");

        assertThat(queenMoveStrategy.isMovable(board, source, target)).isTrue();
    }

    @Test
    @DisplayName("퀸이 수평 이동할 수 있다.")
    void isMovableHorizontal() {
        board.movePiece(Position.valueOf("c8"), Position.valueOf("c6"));

        Position source = Position.valueOf("d8");
        Position target = Position.valueOf("c8");

        assertThat(queenMoveStrategy.isMovable(board, source, target)).isTrue();
    }

    @Test
    @DisplayName("퀸의 이동전략이 아니다.")
    void isNotQueenMovePattern() {
        Position source = Position.valueOf("d8");
        Position target = Position.valueOf("c6");

        assertThat(queenMoveStrategy.isMovable(board, source, target)).isFalse();
    }

}