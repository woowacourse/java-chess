package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.Column;
import chess.Position;
import chess.Route;
import chess.Row;
import chess.Team;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PawnTest {

    private final Position position = new Position(Column.D, Row.THREE);
    private Pawn blackPawn;
    private Pawn whitePawn;

    @BeforeEach
    void setUp() {
        blackPawn = new Pawn(Team.BLACK);
        whitePawn = new Pawn(Team.WHITE);
    }

    @Test
    void moveUp() {

        assertThat(whitePawn.moveUp(position)).isEqualTo(Route.from(new Position(Column.D, Row.FOUR)));
    }

    @Test
    void moveUpFail() {

        Assertions.assertThatThrownBy(() -> blackPawn.moveUp(position))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void moveDown() {

        assertThat(blackPawn.moveDown(position)).isEqualTo(Route.from(new Position(Column.D, Row.TWO)));
    }

    @Test
    void moveDownFail() {
        Assertions.assertThatThrownBy(() -> whitePawn.moveDown(position))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void moveRight() {

        Assertions.assertThatThrownBy(() -> whitePawn.moveRight(position))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void moveLeft() {

        Assertions.assertThatThrownBy(() -> whitePawn.moveLeft(position))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void moveUpUp() {

        assertThat(whitePawn.moveUpUp(position)).isEqualTo(new Route(
                List.of(
                        new Position(Column.D, Row.FOUR),
                        new Position(Column.D, Row.FIVE)
                )));
    }


    @Test
    void moveDownDown() {

        assertThat(blackPawn.moveDownDown(position)).isEqualTo(new Route(
                List.of(
                        new Position(Column.D, Row.TWO),
                        new Position(Column.D, Row.ONE)
                )));
    }
}
