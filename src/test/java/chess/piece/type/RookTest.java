package chess.piece.type;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import chess.board.ChessBoard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.location.Location;
import chess.team.Team;

class RookTest {
	@Test
	void canMove() {
		Map<Location, Piece> board = new HashMap<>();

		Rook rook = new Rook(Team.BLACK);
		Location now = new Location(8, 'a');
		Location after = new Location(8, 'h');

		boolean actual = rook.canMove(board,now, after);

		assertThat(actual).isTrue();
	}

	@Test
	@DisplayName("갈 수 없는 곳 테스트")
	void cantMove() {
		Map<Location, Piece> board = new HashMap<>();

		Rook rook = new Rook(Team.BLACK);
		Location now = new Location(8, 'a');
		Location cantAfter = new Location(7, 'b');

		boolean cantActual = rook.canMove(board, now, cantAfter);

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

		boolean actual = givenPiece.canMove(board, new Location(1, 'c'), new Location(3, 'c'));
		assertThat(actual).isFalse();
	}

	@DisplayName("룩 가로 목적지 중간에 장애물이 있는지 확인")
	@Test
	void name2() {
		Map<Location, Piece> board = new HashMap<>();
		Bishop givenPiece = new Bishop(Team.BLACK);
		board.put(new Location(1, 'a'), givenPiece);
		board.put(new Location(1, 'b'), new Bishop(Team.WHITE));
		board.put(new Location(1, 'c'), new Bishop(Team.WHITE));

		boolean actual = givenPiece.canMove(board, new Location(1, 'a'), new Location(1, 'c'));
		assertThat(actual).isFalse();
	}
}