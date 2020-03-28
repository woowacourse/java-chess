package chess.piece;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.board.Location;
import chess.team.Team;

class RookTest {
	@Test
	void canMove() {
		Rook rook = new Rook(Team.BLACK);
		Location now = new Location(8, 'a');
		Location after = new Location(8, 'h');
		boolean actual = rook.canMove(now, after);

		assertThat(actual).isTrue();

		Location cantAfter = new Location(7, 'b');
		boolean cantActual = rook.canMove(now, cantAfter);

		assertThat(cantActual).isFalse();
	}

	@DisplayName("룩 세로 목적지 중간에 장애물이 있는지 확인")
	@Test
	void name() {
		Map<Location, Piece> board = new HashMap<>();
		Bishop givenPiece = new Bishop(Team.BLACK);
		board.put(new Location(1, 'c'), givenPiece);
		board.put(new Location(2, 'c'), new Bishop(Team.WHITE));
		board.put(new Location(3, 'c'), new Bishop(Team.WHITE));

		boolean actual = givenPiece.hasObstacle(board, new Location(1, 'c'), new Location(3, 'c'));
		assertThat(actual).isTrue();
	}

	@DisplayName("룩 가로 목적지 중간에 장애물이 있는지 확인")
	@Test
	void name2() {
		Map<Location, Piece> board = new HashMap<>();
		Bishop givenPiece = new Bishop(Team.BLACK);
		board.put(new Location(1, 'a'), givenPiece);
		board.put(new Location(1, 'b'), new Bishop(Team.WHITE));
		board.put(new Location(1, 'c'), new Bishop(Team.WHITE));

		boolean actual = givenPiece.hasObstacle(board, new Location(1, 'a'), new Location(1, 'c'));
		assertThat(actual).isTrue();
	}
}