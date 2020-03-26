package chess.piece.type;

import chess.board.Location;
import chess.team.Team;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class QueenTest {
    @Test
    void canMove() {
        Queen queen = new Queen(Team.BLACK);
        Location now = new Location(8, 'd');
        Location after = new Location(8, 'h');
        boolean actual = queen.canMove(now, after);

        assertThat(actual).isTrue();

        Location cantAfter = new Location(7, 'f');
        boolean cantActual = queen.canMove(now, cantAfter);

        assertThat(cantActual).isFalse();
    }


}