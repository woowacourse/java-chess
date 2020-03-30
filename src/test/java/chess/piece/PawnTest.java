package chess.piece;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.board.Location;
import chess.team.Team;

class PawnTest {
	@Test
	@DisplayName("초기 위치의 폰의 이동 반경 테스트")
	void canMove() {
		Pawn pawn = Pawn.of(Team.BLACK);
		Location now = Location.of(7, 'a');

		Location moveTwiceForward = Location.of(5, 'a');
		boolean moveTwiceForwardActual = pawn.checkRange(now, moveTwiceForward);
		assertThat(moveTwiceForwardActual).isTrue();

		Location moveOnceForward = Location.of(6, 'a');
		boolean moveOnceForwardActual = pawn.checkRange(now, moveOnceForward);
		assertThat(moveOnceForwardActual).isTrue();

		Location moveDiagonal = Location.of(6, 'b');
		boolean moveDiagonalAcutal = pawn.checkRange(now, moveDiagonal);
		assertThat(moveDiagonalAcutal).isTrue();

		Location cantAfter = Location.of(4, 'a');
		boolean cantActual = pawn.checkRange(now, cantAfter);

		assertThat(cantActual).isFalse();
	}

	@Test
	@DisplayName("초기 위치가 아닌 일반적인 폰의 이동")
	void canMove2() {
		Pawn pawn = Pawn.of(Team.BLACK);
		Location now = Location.of(6, 'a');

		Location moveOnceForward = Location.of(5, 'a');
		boolean moveOnceForwardActual = pawn.checkRange(now, moveOnceForward);
		assertThat(moveOnceForwardActual).isTrue();

		Location moveDiagonal = Location.of(5, 'b');
		boolean moveDiagonalAcutal = pawn.checkRange(now, moveDiagonal);
		assertThat(moveDiagonalAcutal).isTrue();

		Location moveTwiceForward = Location.of(4, 'a');
		boolean moveTwiceForwardActual = pawn.checkRange(now, moveTwiceForward);
		assertThat(moveTwiceForwardActual).isFalse();
	}

	@DisplayName("폰의 대각선 위치에 적이 있는 경우")
	@Test
	void name() {
		Map<Location, Piece> board = new HashMap<>();
		Pawn givenPiece = Pawn.of(Team.BLACK);
		board.put(Location.of(7, 'a'), givenPiece);
		board.put(Location.of(6, 'b'), Bishop.of(Team.WHITE));

		boolean actual = givenPiece.checkObstacle(board, Location.of(7, 'a'), Location.of(6, 'b'));
		assertThat(actual).isFalse();
	}

	@DisplayName("폰의 대각선 위치에 적이 없는 경우")
	@Test
	void name2() {
		Map<Location, Piece> board = new HashMap<>();
		Pawn givenPiece = Pawn.of(Team.BLACK);
		board.put(Location.of(7, 'a'), givenPiece);

		boolean actual = givenPiece.checkObstacle(board, Location.of(7, 'a'), Location.of(6, 'b'));
		assertThat(actual).isTrue();
	}

	@DisplayName("폰의 두 칸의 직선 위치로 가는 중 적이 있는 경우")
	@Test
	void name3() {
		Map<Location, Piece> board = new HashMap<>();
		Pawn givenPiece = Pawn.of(Team.BLACK);
		Pawn counterPiece = Pawn.of(Team.WHITE);
		Pawn destinaionPiece = Pawn.of(Team.WHITE);

		board.put(Location.of(7, 'a'), givenPiece);
		board.put(Location.of(6, 'a'), counterPiece);
		board.put(Location.of(5, 'a'), destinaionPiece);

		boolean actual = givenPiece.checkObstacle(board, Location.of(7, 'a'), Location.of(5, 'a'));
		assertThat(actual).isTrue();
	}

	@DisplayName("폰의 직선 위치에 적이 있는 경우")
	@Test
	void name4() {
		Map<Location, Piece> board = new HashMap<>();
		Pawn givenPiece = Pawn.of(Team.BLACK);
		Pawn counterPiece = Pawn.of(Team.WHITE);
		board.put(Location.of(7, 'a'), givenPiece);
		board.put(Location.of(6, 'a'), counterPiece);

		boolean actual = givenPiece.checkObstacle(board, Location.of(7, 'a'), Location.of(6, 'a'));
		assertThat(actual).isTrue();
	}

	@DisplayName("폰의 직선 위치에 적이 있는 경우")
	@Test
	void name5() {
		Map<Location, Piece> board = new HashMap<>();
		Pawn givenPiece = Pawn.of(Team.BLACK);
		Pawn destinaionPiece = Pawn.of(Team.WHITE);
		board.put(Location.of(7, 'a'), givenPiece);
		board.put(Location.of(5, 'a'), destinaionPiece);

		boolean actual = givenPiece.checkObstacle(board, Location.of(7, 'a'), Location.of(5, 'a'));
		assertThat(actual).isTrue();
	}
}