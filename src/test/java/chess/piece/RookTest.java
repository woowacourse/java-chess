package chess.piece;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.board.Location;
import chess.team.Team;

class RookTest {
	@DisplayName("룩의 이동범위 테스트")
	@Test
	void canMove() {
		Rook rook = Rook.of(Team.BLACK);
		Location now = Location.of('a', 8);
		Location after = Location.of('h', 8);
		boolean actual = rook.checkRange(now, after);

		assertThat(actual).isTrue();

		Location cantAfter = Location.of('b', 7);
		boolean cantActual = rook.checkRange(now, cantAfter);

		assertThat(cantActual).isFalse();
	}

	@DisplayName("룩 세로 목적지 중간에 장애물이 있는지 확인")
	@Test
	void name() {
		Map<Location, Piece> board = new HashMap<>();
		Bishop givenPiece = Bishop.of(Team.BLACK);
		board.put(Location.of('c', 1), givenPiece);
		board.put(Location.of('c', 2), Bishop.of(Team.WHITE));
		board.put(Location.of('c', 3), Bishop.of(Team.WHITE));

		boolean actual = givenPiece.checkObstacle(board, Location.of('c', 1), Location.of('c', 3));
		assertThat(actual).isTrue();
	}

	@DisplayName("룩 가로 목적지 중간에 장애물이 있는지 확인")
	@Test
	void name2() {
		Map<Location, Piece> board = new HashMap<>();
		Bishop givenPiece = Bishop.of(Team.BLACK);
		board.put(Location.of('a', 1), givenPiece);
		board.put(Location.of('b', 1), Bishop.of(Team.WHITE));
		board.put(Location.of('c', 1), Bishop.of(Team.WHITE));

		boolean actual = givenPiece.checkObstacle(board, Location.of('a', 1), Location.of('c', 1));
		assertThat(actual).isTrue();
	}
}