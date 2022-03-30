package chess.model.piece;

import static chess.model.piece.Fixtures.A4;
import static chess.model.piece.Fixtures.B2;
import static chess.model.piece.Fixtures.B3;
import static chess.model.piece.Fixtures.B5;
import static chess.model.piece.Fixtures.B6;
import static chess.model.piece.Fixtures.C2;
import static chess.model.piece.Fixtures.C6;
import static chess.model.piece.Fixtures.D1;
import static chess.model.piece.Fixtures.D4;
import static chess.model.piece.Fixtures.D8;
import static chess.model.piece.Fixtures.E2;
import static chess.model.piece.Fixtures.E6;
import static chess.model.piece.Fixtures.F2;
import static chess.model.piece.Fixtures.F3;
import static chess.model.piece.Fixtures.F5;
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

public class QueenTest {

    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
    }
    
    @Test
    void movable() {
        Queen queen = new Queen(Color.BLACK);
        assertAll(
                () -> assertThat(queen.movable(D4, B6)).isTrue(),
                () -> assertThat(queen.movable(D4, B2)).isTrue(),
                () -> assertThat(queen.movable(D4, F6)).isTrue(),
                () -> assertThat(queen.movable(D4, F2)).isTrue(),
                () -> assertThat(queen.movable(D4, D8)).isTrue(),
                () -> assertThat(queen.movable(D4, D1)).isTrue(),
                () -> assertThat(queen.movable(D4, A4)).isTrue(),
                () -> assertThat(queen.movable(D4, H4)).isTrue());
    }

    @Test
    void cannotMovable() {
        Queen queen = new Queen(Color.BLACK);
        assertAll(
                () -> assertThat(queen.movable(D4, E6)).isFalse(),
                () -> assertThat(queen.movable(D4, F5)).isFalse(),
                () -> assertThat(queen.movable(D4, F3)).isFalse(),
                () -> assertThat(queen.movable(D4, E2)).isFalse(),
                () -> assertThat(queen.movable(D4, C2)).isFalse(),
                () -> assertThat(queen.movable(D4, B3)).isFalse(),
                () -> assertThat(queen.movable(D4, B5)).isFalse(),
                () -> assertThat(queen.movable(D4, C6)).isFalse());
    }


    @Test
    void cannotMovableToSameColor() {
        Queen queen = new Queen(Color.BLACK);
        Square source = Square.of(File.D, Rank.EIGHT);
        Square target = Square.of(File.D, Rank.SEVEN);
        assertThat(queen.isObstacleOnRoute(board, source, target)).isFalse();
    }

    @Test
    void cannotMovableAboveObstacle() {
        Queen queen = new Queen(Color.BLACK);
        Square source = Square.of(File.D, Rank.EIGHT);
        Square target = Square.of(File.D, Rank.SIX);
        assertThat(queen.isObstacleOnRoute(board, source, target)).isFalse();
    }
}
