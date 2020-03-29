package chess.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import chess.board.Location;
import chess.team.Team;

class KnightTest {
	@Test
	void canMove() {
		Knight knight = new Knight(Team.BLACK);
		Location now = Location.of(8, 'g');

		Location leftAfter = Location.of(7, 'e');
		boolean leftAfterActual = knight.canMove(now, leftAfter);
		assertThat(leftAfterActual).isTrue();

		Location rightAfter = Location.of(6, 'h');
		boolean rightAfterActual = knight.canMove(now, rightAfter);
		assertThat(rightAfterActual).isTrue();

		Location cantAfter = Location.of(2, 'c');
		boolean cantActual = knight.canMove(now, cantAfter);
		assertThat(cantActual).isFalse();
	}

}