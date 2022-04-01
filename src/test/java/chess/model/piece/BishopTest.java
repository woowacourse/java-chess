package chess.model.piece;

import static chess.model.piece.Fixtures.B2;
import static chess.model.piece.Fixtures.B6;
import static chess.model.piece.Fixtures.D4;
import static chess.model.piece.Fixtures.D5;
import static chess.model.piece.Fixtures.F2;
import static chess.model.piece.Fixtures.F6;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.model.Board;
import chess.model.square.File;
import chess.model.square.Rank;
import chess.model.square.Square;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BishopTest {

    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
    }

    @Test
    void createBishop() {
        Bishop bishop = new Bishop(Color.BLACK);
        assertThat(bishop).isInstanceOf(Bishop.class);
    }

    @Test
    void movable() {
        Bishop bishop = new Bishop(Color.BLACK);
        assertAll(
                () -> assertThat(bishop.movable(D4, B6)).isTrue(),
                () -> assertThat(bishop.movable(D4, B2)).isTrue(),
                () -> assertThat(bishop.movable(D4, F6)).isTrue(),
                () -> assertThat(bishop.movable(D4, F2)).isTrue());
    }

    @Test
    void cannotMovable() {
        Bishop bishop = new Bishop(Color.BLACK);
        assertThat(bishop.movable(D4, D5)).isFalse();
    }

    @Test
    void cannotMovableToSameColor() {
        Bishop bishop = new Bishop(Color.BLACK);
        Square source = Square.of(File.B, Rank.EIGHT);
        Square target = Square.of(File.A, Rank.SEVEN);
        assertThat(bishop.canMoveWithoutObstacle(board, source, target)).isFalse();
    }

    @Test
    void cannotMovableAboveObstacle() {
        Bishop bishop = new Bishop(Color.BLACK);
        Square source = Square.of(File.B, Rank.EIGHT);
        Square target = Square.of(File.D, Rank.SIX);
        assertThat(bishop.canMoveWithoutObstacle(board, source, target)).isFalse();
    }
}
