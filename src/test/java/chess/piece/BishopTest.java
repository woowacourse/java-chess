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
		Bishop bishop = new Bishop(Team.BLACK);
		Location now = Location.of(8, 'c');
		Location after = Location.of(7, 'd');
		boolean actual = bishop.canMove(now, after);

		assertThat(actual).isTrue();

		Location cantAfter = Location.of(2, 'c');
		boolean cantActual = bishop.canMove(now, cantAfter);

		assertThat(cantActual).isFalse();
	}

	@DisplayName("비숍의 목적지 중간에 장애물이 있는지 확인")
	@Test
	void name() {
		Map<Location, Piece> board = new HashMap<>();
		Bishop givenPiece = new Bishop(Team.BLACK);
		board.put(Location.of(1, 'c'), givenPiece);
		board.put(Location.of(2, 'd'), new Bishop(Team.WHITE));
		board.put(Location.of(3, 'e'), new Bishop(Team.WHITE));

		boolean actual = givenPiece.hasObstacle(board, Location.of(1, 'c'), Location.of(3, 'e'));
		assertThat(actual).isTrue();
	}
}