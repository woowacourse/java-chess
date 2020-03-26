package chess.piece.type;

import chess.board.Location;
import chess.team.Team;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {
    @Test
    void canMove() {
        Knight knight = new Knight(Team.BLACK);
        Location now = new Location(8, 'g');

        Location leftAfter = new Location(7, 'e');
        boolean leftAfterActual = knight.canMove(now, leftAfter);
        assertThat(leftAfterActual).isTrue();

        Location rightAfter = new Location(6, 'h');
        boolean rightAfterActual = knight.canMove(now, rightAfter);
        assertThat(rightAfterActual).isTrue();

        Location cantAfter = new Location(2, 'c');
        boolean cantActual = knight.canMove(now, cantAfter);
        assertThat(cantActual).isFalse();
    }

}