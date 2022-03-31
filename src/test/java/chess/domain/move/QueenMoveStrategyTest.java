package chess.domain.move;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.CatchPieces;
import chess.domain.board.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QueenMoveStrategyTest {

    private Board board;
    private QueenMoveStrategy queenMoveStrategy;
    private CatchPieces catchPieces;

    @BeforeEach
    void setUp() {
        board = BoardFactory.createChessBoard();
        queenMoveStrategy = new QueenMoveStrategy();
        catchPieces = new CatchPieces();
    }

    @Test
    @DisplayName("퀸이 수직 이동할 수 있다.")
    void isMovable_Vertical() {
        board.movePiece(Position.valueOf("d2"), Position.valueOf("e3"), catchPieces);

        Position source = Position.valueOf("d1");
        Position target = Position.valueOf("d4");

        assertThat(queenMoveStrategy.isMovable(board, source, target)).isTrue();
    }

    @Test
    @DisplayName("퀸이 수평 이동할 수 있다.")
    void isMovableHorizon() {
        board.movePiece(Position.valueOf("e1"), Position.valueOf("e3"), catchPieces);

        Position source = Position.valueOf("d1");
        Position target = Position.valueOf("e1");

        assertThat(queenMoveStrategy.isMovable(board, source, target)).isTrue();
    }

    @Test
    @DisplayName("수직이동시 중간에 다른 기물이 존재하면 false")
    void isMovable_Vertical_WhenExistOtherPiece() {
        Position source = Position.valueOf("d1");
        Position target = Position.valueOf("d4");

        assertThat(queenMoveStrategy.isMovable(board, source, target)).isFalse();
    }

    @Test
    @DisplayName("수평이동시 중간에 다른 기물이 존재하면 false")
    void isMovable_Horizontal_WhenExistOtherPiece() {
        Position source = Position.valueOf("d1");
        Position target = Position.valueOf("e1");

        assertThat(queenMoveStrategy.isMovable(board, source, target)).isFalse();
    }

    @Test
    @DisplayName("퀸이 양의 대각선으로 이동 할 수 있다.")
    void isMovable_PositiveDiagonal() {
        board.movePiece(Position.valueOf("e2"), Position.valueOf("e4"), catchPieces);

        Position source = Position.valueOf("d1");
        Position target = Position.valueOf("e2");

        assertThat(queenMoveStrategy.isMovable(board, source, target)).isTrue();
    }

    @Test
    @DisplayName("퀸이 음의 대각선으로 이동 할 수 있다.")
    void isMovable_NegativeDiagonal() {
        board.movePiece(Position.valueOf("c2"), Position.valueOf("c4"), catchPieces);

        Position source = Position.valueOf("d1");
        Position target = Position.valueOf("c2");

        assertThat(queenMoveStrategy.isMovable(board, source, target)).isTrue();
    }

    @Test
    @DisplayName("양의 대각선으로 이동시 중간에 다른 기물이 존재하면 false")
    void isMovable_PositiveDiagonal_WhenExistOtherPiece() {
        Position source = Position.valueOf("d1");
        Position target = Position.valueOf("e2");

        assertThat(queenMoveStrategy.isMovable(board, source, target)).isFalse();
    }

    @Test
    @DisplayName("음의 대각선으로 이동시 중간에 다른 기물이 존재하면 false")
    void isMovable_NegativeDiagonal_WhenExistOtherPiece() {
        Position source = Position.valueOf("d1");
        Position target = Position.valueOf("c2");

        assertThat(queenMoveStrategy.isMovable(board, source, target)).isFalse();
    }
}