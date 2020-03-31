package chess.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import chess.board.Location;
import chess.team.Team;

class KnightTest {
	@Test
	void canMove() {
		Knight knight = Knight.of(Team.BLACK);
		Location now = Location.of('g', 8);

		Location leftAfter = Location.of('e', 7);
		boolean leftAfterActual = knight.checkRange(now, leftAfter);
		assertThat(leftAfterActual).isTrue();

		Location rightAfter = Location.of('h', 6);
		boolean rightAfterActual = knight.checkRange(now, rightAfter);
		assertThat(rightAfterActual).isTrue();

		Location cantAfter = Location.of('c', 2);
		boolean cantActual = knight.checkRange(now, cantAfter);
		assertThat(cantActual).isFalse();
	}

}