package chess.domain.move;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.CatchPieces;
import chess.domain.board.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BishopMoveStrategyTest {

    private Board board;
    private BishopMoveStrategy bishopMoveStrategy;
    private CatchPieces catchPieces;

    @BeforeEach
    void setUp() {
        board = BoardFactory.createChessBoard();
        bishopMoveStrategy = new BishopMoveStrategy();
        catchPieces = new CatchPieces();
    }

    @Test
    @DisplayName("비숍이 양의 대각선으로 이동 할 수 있다.")
    void isMovable_PositiveDiagonal() {
        board.movePiece(Position.valueOf("d7"), Position.valueOf("d6"), catchPieces);

        Position source = Position.valueOf("c8");
        Position target = Position.valueOf("e6");

        assertThat(bishopMoveStrategy.isMovable(board, source, target)).isTrue();
    }

    @Test
    @DisplayName("비숍이 음의 대각선으로 이동 할 수 있다.")
    void isMovable_NegativeDiagonal() {
        board.movePiece(Position.valueOf("b7"), Position.valueOf("d6"), catchPieces);

        Position source = Position.valueOf("c8");
        Position target = Position.valueOf("a6");

        assertThat(bishopMoveStrategy.isMovable(board, source, target)).isTrue();
    }

    @Test
    @DisplayName("양의 대각선으로 이동시 중간에 다른 기물이 존재하면 false")
    void isMovable_PositiveDiagonal_WhenExistOtherPiece() {
        Position source = Position.valueOf("c8");
        Position target = Position.valueOf("a6");

        assertThat(bishopMoveStrategy.isMovable(board, source, target)).isFalse();
    }

    @Test
    @DisplayName("음의 대각선으로 이동시 중간에 다른 기물이 존재하면 false")
    void isMovable_NegativeDiagonal_WhenExistOtherPiece() {
        Position source = Position.valueOf("c8");
        Position target = Position.valueOf("e6");

        assertThat(bishopMoveStrategy.isMovable(board, source, target)).isFalse();
    }
}
