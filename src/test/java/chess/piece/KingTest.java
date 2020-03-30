package chess.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import chess.board.Location;
import chess.team.Team;

class KingTest {

	@Test
	void canMove() {
		King king = King.of(Team.BLACK);
		Location now = Location.of(8, 'e');

		Location after = Location.of(7, 'e');
		boolean actual = king.checkRange(now, after);
		assertThat(actual).isTrue();

		Location cantAfter = Location.of(6, 'c');
		boolean cantActual = king.checkRange(now, cantAfter);
		assertThat(cantActual).isFalse();
	}
}