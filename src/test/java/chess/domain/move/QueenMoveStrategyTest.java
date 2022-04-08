package chess.domain.move;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QueenMoveStrategyTest {

    private Board board;
    private QueenMoveStrategy queenMoveStrategy;

    @BeforeEach
    void setUp() {
        board = BoardFactory.createInitChessBoard();
        queenMoveStrategy = new QueenMoveStrategy();
    }

    @Test
    @DisplayName("퀸이 수직 이동할 수 있다.")
    void isMovable_Vertical() {
        board.movePiece(Position.of('d', 2), Position.of('e', 3));

        Position source = Position.of('d', 1);
        Position target = Position.of('d', 4);

        assertThat(queenMoveStrategy.isMovable(board, source, target)).isTrue();
    }

    @Test
    @DisplayName("퀸이 수평 이동할 수 있다.")
    void isMovableHorizon() {
        board.movePiece(Position.of('e', 1), Position.of('e', 3));

        Position source = Position.of('d', 1);
        Position target = Position.of('e', 1);

        assertThat(queenMoveStrategy.isMovable(board, source, target)).isTrue();
    }

    @Test
    @DisplayName("수직이동시 중간에 다른 기물이 존재하면 false")
    void isMovable_Vertical_WhenExistOtherPiece() {
        Position source = Position.of('d', 1);
        Position target = Position.of('d', 4);

        assertThat(queenMoveStrategy.isMovable(board, source, target)).isFalse();
    }

    @Test
    @DisplayName("수평이동시 중간에 다른 기물이 존재하면 false")
    void isMovable_Horizontal_WhenExistOtherPiece() {
        Position source = Position.of('d', 1);
        Position target = Position.of('e', 1);

        assertThat(queenMoveStrategy.isMovable(board, source, target)).isFalse();
    }

    @Test
    @DisplayName("퀸이 양의 대각선으로 이동 할 수 있다.")
    void isMovable_PositiveDiagonal() {
        board.movePiece(Position.of('e', 2), Position.of('e', 4));

        Position source = Position.of('d', 1);
        Position target = Position.of('e', 2);

        assertThat(queenMoveStrategy.isMovable(board, source, target)).isTrue();
    }

    @Test
    @DisplayName("퀸이 음의 대각선으로 이동 할 수 있다.")
    void isMovable_NegativeDiagonal() {
        board.movePiece(Position.of('c', 2), Position.of('c', 4));

        Position source = Position.of('d', 1);
        Position target = Position.of('c', 2);

        assertThat(queenMoveStrategy.isMovable(board, source, target)).isTrue();
    }

    @Test
    @DisplayName("양의 대각선으로 이동시 중간에 다른 기물이 존재하면 false")
    void isMovable_PositiveDiagonal_WhenExistOtherPiece() {
        Position source = Position.of('d', 1);
        Position target = Position.of('e', 2);

        assertThat(queenMoveStrategy.isMovable(board, source, target)).isFalse();
    }

    @Test
    @DisplayName("음의 대각선으로 이동시 중간에 다른 기물이 존재하면 false")
    void isMovable_NegativeDiagonal_WhenExistOtherPiece() {
        Position source = Position.of('d', 1);
        Position target = Position.of('c', 2);

        assertThat(queenMoveStrategy.isMovable(board, source, target)).isFalse();
    }
}