package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.Column;
import chess.Position;
import chess.Route;
import chess.Row;
import chess.Team;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RookTest {

    private Rook rook;
    private final Position position = new Position(Column.D, Row.FOUR);

    @BeforeEach
    void setUp() {
        rook = new Rook(Team.BLACK);
    }

    @Test
    void moveUp() {

        assertThat(rook.moveUp(position)).isEqualTo(new Route(
                List.of(
                        new Position(Column.D, Row.FIVE),
                        new Position(Column.D, Row.SIX),
                        new Position(Column.D, Row.SEVEN),
                        new Position(Column.D, Row.EIGHT)
                )));
    }

    @Test
    void moveDown() {

        assertThat(rook.moveDown(position)).isEqualTo(new Route(
                List.of(
                        new Position(Column.D, Row.THREE),
                        new Position(Column.D, Row.TWO),
                        new Position(Column.D, Row.ONE)
                )));
    }

    @Test
    void moveRight() {

        assertThat(rook.moveRight(position)).isEqualTo(new Route(
                List.of(
                        new Position(Column.E, Row.FOUR),
                        new Position(Column.F, Row.FOUR),
                        new Position(Column.G, Row.FOUR),
                        new Position(Column.H, Row.FOUR)
                )));
    }

    @Test
    void moveLeft() {

        assertThat(rook.moveLeft(position)).isEqualTo(new Route(
                List.of(
                        new Position(Column.C, Row.FOUR),
                        new Position(Column.B, Row.FOUR),
                        new Position(Column.A, Row.FOUR)
                )));
    }
}
