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

class QueenTest {

    private Queen queen;
    private final Position position = new Position(Column.D, Row.FOUR);

    @BeforeEach
    void setUp() {
        queen = new Queen(Team.BLACK);
    }

    @Test
    void moveRightUp() {

        assertThat(queen.moveRightUp(position)).isEqualTo(new Route(
                List.of(
                        new Position(Column.E, Row.FIVE),
                        new Position(Column.F, Row.SIX),
                        new Position(Column.G, Row.SEVEN),
                        new Position(Column.H, Row.EIGHT)
                )));
    }

    @Test
    void moveRightDown() {

        assertThat(queen.moveRightDown(position)).isEqualTo(new Route(
                List.of(
                        new Position(Column.E, Row.THREE),
                        new Position(Column.F, Row.TWO),
                        new Position(Column.G, Row.ONE)
                )));
    }

    @Test
    void moveLeftUp() {

        assertThat(queen.moveLeftUp(position)).isEqualTo(new Route(
                List.of(
                        new Position(Column.C, Row.FIVE),
                        new Position(Column.B, Row.SIX),
                        new Position(Column.A, Row.SEVEN)
                )));
    }

    @Test
    void moveLeftDown() {

        assertThat(queen.moveLeftDown(position)).isEqualTo(new Route(
                List.of(
                        new Position(Column.C, Row.THREE),
                        new Position(Column.B, Row.TWO),
                        new Position(Column.A, Row.ONE)
                )));
    }

    @Test
    void moveUp() {

        assertThat(queen.moveUp(position)).isEqualTo(new Route(
                List.of(
                        new Position(Column.D, Row.FIVE),
                        new Position(Column.D, Row.SIX),
                        new Position(Column.D, Row.SEVEN),
                        new Position(Column.D, Row.EIGHT)
                )));
    }

    @Test
    void moveDown() {

        assertThat(queen.moveDown(position)).isEqualTo(new Route(
                List.of(
                        new Position(Column.D, Row.THREE),
                        new Position(Column.D, Row.TWO),
                        new Position(Column.D, Row.ONE)
                )));
    }

    @Test
    void moveRight() {

        assertThat(queen.moveRight(position)).isEqualTo(new Route(
                List.of(
                        new Position(Column.E, Row.FOUR),
                        new Position(Column.F, Row.FOUR),
                        new Position(Column.G, Row.FOUR),
                        new Position(Column.H, Row.FOUR)
                )));
    }

    @Test
    void moveLeft() {

        assertThat(queen.moveLeft(position)).isEqualTo(new Route(
                List.of(
                        new Position(Column.C, Row.FOUR),
                        new Position(Column.B, Row.FOUR),
                        new Position(Column.A, Row.FOUR)
                )));
    }
}
