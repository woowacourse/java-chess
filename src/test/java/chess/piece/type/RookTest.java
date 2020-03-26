package chess.piece.type;

import chess.board.Location;
import chess.team.Team;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RookTest {
    @Test
    void canMove() {
        Rook rook = new Rook(Team.BLACK);
        Location now = new Location(8, 'a');
        Location after = new Location(8, 'h');
        boolean actual = rook.canMove(now, after);

        assertThat(actual).isTrue();

        Location cantAfter = new Location(7, 'b');
        boolean cantActual = rook.canMove(now, cantAfter);

        assertThat(cantActual).isFalse();
    }

}