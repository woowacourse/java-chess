package chess.piece;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.board.Location;
import chess.team.Team;

class BishopTest {
	@Test
	void canMove() {
		Bishop bishop = Bishop.of(Team.BLACK);
		Location now = Location.of('c', 8);
		Location after = Location.of('d', 7);
		boolean actual = bishop.checkRange(now, after);

		assertThat(actual).isTrue();

		Location cantAfter = Location.of('c', 2);
		boolean cantActual = bishop.checkRange(now, cantAfter);

		assertThat(cantActual).isFalse();
	}

	@DisplayName("비숍의 목적지 중간에 장애물이 있는지 확인")
	@Test
	void name() {
		Map<Location, Piece> board = new HashMap<>();
		Bishop givenPiece = Bishop.of(Team.BLACK);
		board.put(Location.of('c', 1), givenPiece);
		board.put(Location.of('d', 2), Bishop.of(Team.WHITE));
		board.put(Location.of('e', 3), Bishop.of(Team.WHITE));

		boolean actual = givenPiece.checkObstacle(board, Location.of('c', 1), Location.of('e', 3));
		assertThat(actual).isTrue();
	}
}