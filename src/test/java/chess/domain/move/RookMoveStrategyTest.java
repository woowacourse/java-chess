package chess.domain.move;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RookMoveStrategyTest {

    private Board board;
    private RookMoveStrategy rookMoveStrategy;

    @BeforeEach
    void setUp() {
        board = BoardFactory.createInitChessBoard();
        rookMoveStrategy = new RookMoveStrategy();
    }

    @Test
    @DisplayName("룩이 수직 이동할 수 있다.")
    void isMovable_Vertical() {
        board.movePiece(Position.of('a', 7), Position.of('b', 6));

        Position source = Position.of('a', 8);
        Position target = Position.of('a', 4);

        assertThat(rookMoveStrategy.isMovable(board, source, target)).isTrue();
    }

    @Test
    @DisplayName("룩이 수평 이동할 수 있다.")
    void isMovableHorizon() {
        board.movePiece(Position.of('b', 8), Position.of('b', 6));

        Position source = Position.of('a', 8);
        Position target = Position.of('b', 8);

        assertThat(rookMoveStrategy.isMovable(board, source, target)).isTrue();
    }

    @Test
    @DisplayName("수직이동시 중간에 다른 기물이 존재하면 false")
    void isMovable_Vertical_WhenExistOtherPiece() {
        Position source = Position.of('a', 8);
        Position target = Position.of('a', 4);

        assertThat(rookMoveStrategy.isMovable(board, source, target)).isFalse();
    }

    @Test
    @DisplayName("수평이동시 중간에 다른 기물이 존재하면 false")
    void isMovable_Horizontal_WhenExistOtherPiece() {
        Position source = Position.of('a', 8);
        Position target = Position.of('h', 8);

        assertThat(rookMoveStrategy.isMovable(board, source, target)).isFalse();
    }
}
