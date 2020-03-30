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
		Rook rook = new Rook(Team.BLACK);
		Location now = Location.of(8, 'a');
		Location after = Location.of(8, 'h');
		boolean actual = rook.checkRange(now, after);

		assertThat(actual).isTrue();

		Location cantAfter = Location.of(7, 'b');
		boolean cantActual = rook.checkRange(now, cantAfter);

		assertThat(cantActual).isFalse();
	}

	@DisplayName("룩 세로 목적지 중간에 장애물이 있는지 확인")
	@Test
	void name() {
		Map<Location, Piece> board = new HashMap<>();
		Bishop givenPiece = new Bishop(Team.BLACK);
		board.put(Location.of(1, 'c'), givenPiece);
		board.put(Location.of(2, 'c'), new Bishop(Team.WHITE));
		board.put(Location.of(3, 'c'), new Bishop(Team.WHITE));

		boolean actual = givenPiece.checkObstacle(board, Location.of(1, 'c'), Location.of(3, 'c'));
		assertThat(actual).isTrue();
	}

	@DisplayName("룩 가로 목적지 중간에 장애물이 있는지 확인")
	@Test
	void name2() {
		Map<Location, Piece> board = new HashMap<>();
		Bishop givenPiece = new Bishop(Team.BLACK);
		board.put(Location.of(1, 'a'), givenPiece);
		board.put(Location.of(1, 'b'), new Bishop(Team.WHITE));
		board.put(Location.of(1, 'c'), new Bishop(Team.WHITE));

		boolean actual = givenPiece.checkObstacle(board, Location.of(1, 'a'), Location.of(1, 'c'));
		assertThat(actual).isTrue();
	}
}