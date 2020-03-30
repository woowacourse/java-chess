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
		Queen queen = new Queen(Team.BLACK);
		Location now = Location.of(8, 'd');
		Location after = Location.of(8, 'h');
		boolean actual = queen.checkRange(now, after);

		assertThat(actual).isTrue();

		Location cantAfter = Location.of(7, 'f');
		boolean cantActual = queen.checkRange(now, cantAfter);

		assertThat(cantActual).isFalse();
	}

	@DisplayName("퀸 대각선 목적지 중간에 장애물이 있는지 확인")
	@Test
	void name1() {
		Map<Location, Piece> board = new HashMap<>();
		Piece givenPiece = new Queen(Team.BLACK);
		board.put(Location.of(1, 'c'), givenPiece);
		board.put(Location.of(2, 'd'), new Bishop(Team.WHITE));
		board.put(Location.of(3, 'e'), new Bishop(Team.WHITE));

		boolean actual = givenPiece.checkObstacle(board, Location.of(1, 'c'), Location.of(3, 'e'));
		assertThat(actual).isTrue();
	}

	@DisplayName("퀸 세로 목적지 중간에 장애물이 있는지 확인")
	@Test
	void name2() {
		Map<Location, Piece> board = new HashMap<>();
		Piece givenPiece = new Queen(Team.BLACK);
		board.put(Location.of(1, 'c'), givenPiece);
		board.put(Location.of(2, 'c'), new Bishop(Team.WHITE));
		board.put(Location.of(3, 'c'), new Bishop(Team.WHITE));

		boolean actual = givenPiece.checkObstacle(board, Location.of(1, 'c'), Location.of(3, 'c'));
		assertThat(actual).isTrue();
	}

	@DisplayName("퀸 가로 목적지 중간에 장애물이 있는지 확인")
	@Test
	void name3() {
		Map<Location, Piece> board = new HashMap<>();
		Piece givenPiece = new Queen(Team.BLACK);
		board.put(Location.of(1, 'c'), givenPiece);
		board.put(Location.of(1, 'd'), new Bishop(Team.WHITE));
		board.put(Location.of(1, 'e'), new Bishop(Team.WHITE));

		boolean actual = givenPiece.checkObstacle(board, Location.of(1, 'c'), Location.of(1, 'e'));
		assertThat(actual).isTrue();

	}
}