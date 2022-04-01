package chess.model.piece;

import static chess.model.piece.Fixtures.B2;
import static chess.model.piece.Fixtures.B6;
import static chess.model.piece.Fixtures.C3;
import static chess.model.piece.Fixtures.C4;
import static chess.model.piece.Fixtures.C5;
import static chess.model.piece.Fixtures.D3;
import static chess.model.piece.Fixtures.D4;
import static chess.model.piece.Fixtures.D5;
import static chess.model.piece.Fixtures.E3;
import static chess.model.piece.Fixtures.E4;
import static chess.model.piece.Fixtures.E5;
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

public class KingTest {

    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
    }
    
    @Test
    void createKing() {
        King king = new King(Color.BLACK);

        assertThat(king).isInstanceOf(King.class);
    }

    @Test
    void movable() {
        King king = new King(Color.BLACK);

        assertAll(
                () -> assertThat(king.movable(D4, C3)).isTrue(),
                () -> assertThat(king.movable(D4, C4)).isTrue(),
                () -> assertThat(king.movable(D4, C5)).isTrue(),
                () -> assertThat(king.movable(D4, D5)).isTrue(),
                () -> assertThat(king.movable(D4, E5)).isTrue(),
                () -> assertThat(king.movable(D4, E4)).isTrue(),
                () -> assertThat(king.movable(D4, E3)).isTrue(),
                () -> assertThat(king.movable(D4, D3)).isTrue());
    }

    @Test
    void cannotMovable() {
        King king = new King(Color.BLACK);

        assertAll(
                () -> assertThat(king.movable(D4, B6)).isFalse(),
                () -> assertThat(king.movable(D4, B2)).isFalse(),
                () -> assertThat(king.movable(D4, F6)).isFalse(),
                () -> assertThat(king.movable(D4, F2)).isFalse());
    }

    @Test
    void cannotMovableToSameColor() {
        King king = new King(Color.BLACK);

        Square source = Square.of(File.E, Rank.EIGHT);
        Square target = Square.of(File.E, Rank.SEVEN);

        assertThat(king.canMoveWithoutObstacle(board, source, target)).isFalse();
    }
}
