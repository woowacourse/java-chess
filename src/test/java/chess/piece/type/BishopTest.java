package chess.piece.type;

import chess.board.Location;
import chess.team.Team;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {
    @Test
    void canMove() {
        Bishop bishop = new Bishop(Team.BLACK);
        Location now = new Location(8, 'c');
        Location after = new Location(7, 'd');
        boolean actual = bishop.canMove(now, after);

        assertThat(actual).isTrue();

        Location cantAfter = new Location(2, 'c');
        boolean cantActual = bishop.canMove(now, cantAfter);

        assertThat(cantActual).isFalse();
    }

}