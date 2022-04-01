package chess.domain.move;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.game.CatchPieces;
import chess.domain.board.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RookMoveStrategyTest {

    private Board board;
    private RookMoveStrategy rookMoveStrategy;
    private CatchPieces catchPieces;

    @BeforeEach
    void setUp() {
        board = BoardFactory.createChessBoard();
        rookMoveStrategy = new RookMoveStrategy();
        catchPieces = new CatchPieces();
    }

    @Test
    @DisplayName("룩이 수직 이동할 수 있다.")
    void isMovable_Vertical() {
        board.movePiece(Position.valueOf("a7"), Position.valueOf("b6"), catchPieces);

        Position source = Position.valueOf("a8");
        Position target = Position.valueOf("a4");

        assertThat(rookMoveStrategy.isMovable(board, source, target)).isTrue();
    }

    @Test
    @DisplayName("룩이 수평 이동할 수 있다.")
    void isMovableHorizon() {
        board.movePiece(Position.valueOf("b8"), Position.valueOf("b6"), catchPieces);

        Position source = Position.valueOf("a8");
        Position target = Position.valueOf("b8");

        assertThat(rookMoveStrategy.isMovable(board, source, target)).isTrue();
    }

    @Test
    @DisplayName("수직이동시 중간에 다른 기물이 존재하면 false")
    void isMovable_Vertical_WhenExistOtherPiece() {
        Position source = Position.valueOf("a8");
        Position target = Position.valueOf("a4");

        assertThat(rookMoveStrategy.isMovable(board, source, target)).isFalse();
    }

    @Test
    @DisplayName("수평이동시 중간에 다른 기물이 존재하면 false")
    void isMovable_Horizontal_WhenExistOtherPiece() {
        Position source = Position.valueOf("a8");
        Position target = Position.valueOf("h8");

        assertThat(rookMoveStrategy.isMovable(board, source, target)).isFalse();
    }
}
