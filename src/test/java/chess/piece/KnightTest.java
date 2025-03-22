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

class KnightTest {

    private Knight knight;
    private final Position position = new Position(Column.D, Row.FOUR);

    @BeforeEach
    void setUp() {
        knight = new Knight(Team.BLACK);
    }

    @Test
    void moveUpRightUp() {

        assertThat(knight.moveUpRightUp(position)).isEqualTo(new Route(
                List.of(
                        new Position(Column.D, Row.FIVE),
                        new Position(Column.E, Row.FIVE),
                        new Position(Column.E, Row.SIX)
                )));
    }

    @Test
    void moveUpLeftUp() {

        assertThat(knight.moveUpLeftUp(position)).isEqualTo(new Route(
                List.of(
                        new Position(Column.D, Row.FIVE),
                        new Position(Column.C, Row.FIVE),
                        new Position(Column.C, Row.SIX)
                )));
    }

    @Test
    void moveRightUpRight() {

        assertThat(knight.moveRightUpRight(position)).isEqualTo(new Route(
                List.of(
                        new Position(Column.E, Row.FOUR),
                        new Position(Column.E, Row.FIVE),
                        new Position(Column.F, Row.FIVE)
                )));
    }

    @Test
    void moveRightDownRight() {

        assertThat(knight.moveRightDownRight(position)).isEqualTo(new Route(
                List.of(
                        new Position(Column.E, Row.FOUR),
                        new Position(Column.E, Row.THREE),
                        new Position(Column.F, Row.THREE)
                )));
    }

    @Test
    void moveDownRightDown() {

        assertThat(knight.moveDownRightDown(position)).isEqualTo(new Route(
                List.of(
                        new Position(Column.D, Row.THREE),
                        new Position(Column.E, Row.THREE),
                        new Position(Column.E, Row.TWO)
                )));
    }

    @Test
    void moveDownLeftDown() {

        assertThat(knight.moveDownLeftDown(position)).isEqualTo(new Route(
                List.of(
                        new Position(Column.D, Row.THREE),
                        new Position(Column.C, Row.THREE),
                        new Position(Column.C, Row.TWO)
                )));
    }

    @Test
    void moveLeftUpLeft() {

        assertThat(knight.moveLeftUpLeft(position)).isEqualTo(new Route(
                List.of(
                        new Position(Column.C, Row.FOUR),
                        new Position(Column.C, Row.FIVE),
                        new Position(Column.B, Row.FIVE)
                )));
    }

    @Test
    void moveLeftDownLeft() {

        assertThat(knight.moveLeftDownLeft(position)).isEqualTo(new Route(
                List.of(
                        new Position(Column.C, Row.FOUR),
                        new Position(Column.C, Row.THREE),
                        new Position(Column.B, Row.THREE)
                )));
    }
}
