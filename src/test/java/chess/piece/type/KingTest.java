package chess.piece.type;

import chess.location.Location;
import chess.team.Team;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KingTest {

    @Test
    void canMove() {
        King king = new King(Team.BLACK);
        Location now = new Location(8, 'e');

        Location after = new Location(7, 'e');
        boolean actual = king.canMove(now, after);
        assertThat(actual).isTrue();

        Location cantAfter = new Location(6, 'c');
        boolean cantActual = king.canMove(now, cantAfter);
        assertThat(cantActual).isFalse();
    }
}