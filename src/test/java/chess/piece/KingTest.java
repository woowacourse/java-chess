package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.Column;
import chess.Position;
import chess.Route;
import chess.Row;
import chess.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class KingTest {

    private King king;
    private final Position position = new Position(Column.E, Row.FOUR);

    @BeforeEach
    void setUp() {
        king = new King(Team.BLACK);
    }

    @Test
    void moveRightUp() {

        assertThat(king.moveRightUp(position)).isEqualTo(Route.from(new Position(Column.F, Row.FIVE)));
    }

    @Test
    void moveRightDown() {

        assertThat(king.moveRightDown(position)).isEqualTo(Route.from(new Position(Column.F, Row.THREE)));
    }

    @Test
    void moveLeftUp() {

        assertThat(king.moveLeftUp(position)).isEqualTo(Route.from(new Position(Column.D, Row.FIVE)));
    }

    @Test
    void moveLeftDown() {

        assertThat(king.moveLeftDown(position)).isEqualTo(Route.from(new Position(Column.D, Row.THREE)));
    }

    @Test
    void moveUp() {

        assertThat(king.moveUp(position)).isEqualTo(Route.from(new Position(Column.E, Row.FIVE)));
    }

    @Test
    void moveDown() {

        assertThat(king.moveDown(position)).isEqualTo(Route.from(new Position(Column.E, Row.THREE)));
    }

    @Test
    void moveRight() {

        assertThat(king.moveRight(position)).isEqualTo(Route.from(new Position(Column.F, Row.FOUR)));
    }

    @Test
    void moveLeft() {

        assertThat(king.moveLeft(position)).isEqualTo(Route.from(new Position(Column.D, Row.FOUR)));
    }
}
