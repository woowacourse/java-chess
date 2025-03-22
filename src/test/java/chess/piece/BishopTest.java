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

class BishopTest {

    private Bishop bishop;
    private final Position position = new Position(Column.D, Row.FOUR);

    @BeforeEach
    void setUp() {
        bishop = new Bishop(Team.BLACK);
    }

    @Test
    void moveRightUp() {

        assertThat(bishop.moveRightUp(position)).isEqualTo(new Route(
                List.of(
                        new Position(Column.E, Row.FIVE),
                        new Position(Column.F, Row.SIX),
                        new Position(Column.G, Row.SEVEN),
                        new Position(Column.H, Row.EIGHT)
                )));
    }

    @Test
    void moveRightDown() {

        assertThat(bishop.moveRightDown(position)).isEqualTo(new Route(
                List.of(
                        new Position(Column.E, Row.THREE),
                        new Position(Column.F, Row.TWO),
                        new Position(Column.G, Row.ONE)
                )));
    }

    @Test
    void moveLeftUp() {

        assertThat(bishop.moveLeftUp(position)).isEqualTo(new Route(
                List.of(
                        new Position(Column.C, Row.FIVE),
                        new Position(Column.B, Row.SIX),
                        new Position(Column.A, Row.SEVEN)
                )));
    }

    @Test
    void moveLeftDown() {

        assertThat(bishop.moveLeftDown(position)).isEqualTo(new Route(
                List.of(
                        new Position(Column.C, Row.THREE),
                        new Position(Column.B, Row.TWO),
                        new Position(Column.A, Row.ONE)
                )));
    }
}
