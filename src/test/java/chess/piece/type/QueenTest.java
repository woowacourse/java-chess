package chess.piece.type;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.location.Location;
import chess.team.Team;

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

	@DisplayName("퀸 대각선 목적지 중간에 장애물이 있는지 확인")
	@Test
	void name1() {
		Map<Location, Piece> board = new HashMap<>();
		Piece givenPiece = new Queen(Team.BLACK);
		board.put(new Location(1, 'c'), givenPiece);
		board.put(new Location(2, 'd'), new Bishop(Team.WHITE));
		board.put(new Location(3, 'e'), new Bishop(Team.WHITE));

		boolean actual = givenPiece.hasNotObstacle(board, new Location(1, 'c'), new Location(3, 'e'));
		assertThat(actual).isFalse();
	}

	@DisplayName("퀸 세로 목적지 중간에 장애물이 있는지 확인")
	@Test
	void name2() {
		Map<Location, Piece> board = new HashMap<>();
		Piece givenPiece = new Queen(Team.BLACK);
		board.put(new Location(1, 'c'), givenPiece);
		board.put(new Location(2, 'c'), new Bishop(Team.WHITE));
		board.put(new Location(3, 'c'), new Bishop(Team.WHITE));

		boolean actual = givenPiece.hasNotObstacle(board, new Location(1, 'c'), new Location(3, 'c'));
		assertThat(actual).isFalse();
	}

	@DisplayName("퀸 가로 목적지 중간에 장애물이 있는지 확인")
	@Test
	void name3() {
		Map<Location, Piece> board = new HashMap<>();
		Piece givenPiece = new Queen(Team.BLACK);
		board.put(new Location(1, 'c'), givenPiece);
		board.put(new Location(1, 'd'), new Bishop(Team.WHITE));
		board.put(new Location(1, 'e'), new Bishop(Team.WHITE));

		boolean actual = givenPiece.hasNotObstacle(board, new Location(1, 'c'), new Location(1, 'e'));
		assertThat(actual).isFalse();

	}
}