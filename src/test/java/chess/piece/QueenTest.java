package chess.piece;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.board.Location;
import chess.team.Team;

class QueenTest {
	@Test
	void canMove() {
		Queen queen = Queen.of(Team.BLACK);
		Location now = Location.of('d', 8);
		Location after = Location.of('h', 8);
		boolean actual = queen.checkRange(now, after);

		assertThat(actual).isTrue();

		Location cantAfter = Location.of('f', 7);
		boolean cantActual = queen.checkRange(now, cantAfter);

		assertThat(cantActual).isFalse();
	}

	@DisplayName("퀸 대각선 목적지 중간에 장애물이 있는지 확인")
	@Test
	void name1() {
		Map<Location, Piece> board = new HashMap<>();
		Piece givenPiece = Queen.of(Team.BLACK);
		board.put(Location.of('c', 1), givenPiece);
		board.put(Location.of('d', 2), Bishop.of(Team.WHITE));
		board.put(Location.of('e', 3), Bishop.of(Team.WHITE));

		boolean actual = givenPiece.checkObstacle(board, Location.of('c', 1), Location.of('e', 3));
		assertThat(actual).isTrue();
	}

	@DisplayName("퀸 세로 목적지 중간에 장애물이 있는지 확인")
	@Test
	void name2() {
		Map<Location, Piece> board = new HashMap<>();
		Piece givenPiece = Queen.of(Team.BLACK);
		board.put(Location.of('c', 1), givenPiece);
		board.put(Location.of('c', 2), Bishop.of(Team.WHITE));
		board.put(Location.of('c', 3), Bishop.of(Team.WHITE));

		boolean actual = givenPiece.checkObstacle(board, Location.of('c', 1), Location.of('c', 3));
		assertThat(actual).isTrue();
	}

	@DisplayName("퀸 가로 목적지 중간에 장애물이 있는지 확인")
	@Test
	void name3() {
		Map<Location, Piece> board = new HashMap<>();
		Piece givenPiece = Queen.of(Team.BLACK);
		board.put(Location.of('c', 1), givenPiece);
		board.put(Location.of('d', 1), Bishop.of(Team.WHITE));
		board.put(Location.of('e', 1), Bishop.of(Team.WHITE));

		boolean actual = givenPiece.checkObstacle(board, Location.of('c', 1), Location.of('e', 1));
		assertThat(actual).isTrue();

	}
}