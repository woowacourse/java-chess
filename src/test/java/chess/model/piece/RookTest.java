package chess.model.piece;

import static chess.model.piece.Fixtures.A4;
import static chess.model.piece.Fixtures.B2;
import static chess.model.piece.Fixtures.B6;
import static chess.model.piece.Fixtures.D1;
import static chess.model.piece.Fixtures.D4;
import static chess.model.piece.Fixtures.D8;
import static chess.model.piece.Fixtures.F2;
import static chess.model.piece.Fixtures.F6;
import static chess.model.piece.Fixtures.H4;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.Board;
import chess.model.square.File;
import chess.model.square.Rank;
import chess.model.square.Square;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RookTest {

    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
    }
    
    @Test
    void createRook() {
        Rook rook = new Rook(Color.BLACK);
        assertThat(rook).isInstanceOf(Rook.class);
    }

    @Test
    void movable() {
        Rook rook = new Rook(Color.BLACK);
        assertAll(
                () -> assertThat(rook.movable(D4, H4)).isTrue(),
                () -> assertThat(rook.movable(D4, A4)).isTrue(),
                () -> assertThat(rook.movable(D4, D1)).isTrue(),
                () -> assertThat(rook.movable(D4, D8)).isTrue());
    }

    @Test
    void cannotMovable() {
        Rook rook = new Rook(Color.BLACK);
        assertAll(
                () -> assertThat(rook.movable(D4, B6)).isFalse(),
                () -> assertThat(rook.movable(D4, B2)).isFalse(),
                () -> assertThat(rook.movable(D4, F6)).isFalse(),
                () -> assertThat(rook.movable(D4, F2)).isFalse());
    }

    @Test
    void cannotMovableToSameColor() {
        Rook rook = new Rook(Color.BLACK);
        Square source = Square.of(File.A, Rank.EIGHT);
        Square target = Square.of(File.A, Rank.SEVEN);
        assertThat(rook.isObstacleOnRoute(board, source, target)).isFalse();
    }

    @Test
    void cannotMovableAboveObstacle() {
        Rook rook = new Rook(Color.BLACK);
        Square source = Square.of(File.A, Rank.EIGHT);
        Square target = Square.of(File.A, Rank.SIX);
        assertThat(rook.isObstacleOnRoute(board, source, target)).isFalse();
    }
}
