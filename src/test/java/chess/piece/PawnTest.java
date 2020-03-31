package chess.piece;

//
// class PawnTest {
// 	@Test
// 	@DisplayName("초기 위치의 폰의 이동 반경 테스트")
// 	void canMove() {
// 		Pawn pawn = Pawn.of(Team.BLACK);
// 		Location now = Location.of('a', 7);
//
// 		Location moveTwiceForward = Location.of('a', 5);
// 		boolean moveTwiceForwardActual = pawn.checkRange(now, moveTwiceForward);
// 		assertThat(moveTwiceForwardActual).isTrue();
//
// 		Location moveOnceForward = Location.of('a', 6);
// 		boolean moveOnceForwardActual = pawn.checkRange(now, moveOnceForward);
// 		assertThat(moveOnceForwardActual).isTrue();
//
// 		Location moveDiagonal = Location.of('b', 6);
// 		boolean moveDiagonalActual = pawn.checkRange(now, moveDiagonal);
// 		assertThat(moveDiagonalActual).isTrue();
//
// 		Location cantAfter = Location.of('a', 4);
// 		boolean cantActual = pawn.checkRange(now, cantAfter);
//
// 		assertThat(cantActual).isFalse();
// 	}
//
// 	@Test
// 	@DisplayName("초기 위치가 아닌 일반적인 폰의 이동")
// 	void canMove2() {
// 		Pawn pawn = Pawn.of(Team.BLACK);
// 		Location now = Location.of('a', 6);
//
// 		Location moveOnceForward = Location.of('a', 5);
// 		boolean moveOnceForwardActual = pawn.checkRange(now, moveOnceForward);
// 		assertThat(moveOnceForwardActual).isTrue();
//
// 		Location moveDiagonal = Location.of('b', 5);
// 		boolean moveDiagonalActual = pawn.checkRange(now, moveDiagonal);
// 		assertThat(moveDiagonalActual).isTrue();
//
// 		Location moveTwiceForward = Location.of('a', 4);
// 		boolean moveTwiceForwardActual = pawn.checkRange(now, moveTwiceForward);
// 		assertThat(moveTwiceForwardActual).isFalse();
// 	}
//
// 	@DisplayName("폰의 대각선 위치에 적이 있는 경우")
// 	@Test
// 	void name() {
// 		Map<Location, Piece> board = new HashMap<>();
// 		Pawn givenPiece = Pawn.of(Team.BLACK);
// 		board.put(Location.of('a', 7), givenPiece);
// 		board.put(Location.of('b', 6), Bishop.of(Team.WHITE));
//
// 		boolean actual = givenPiece.checkObstacle(board, Location.of('a', 7), Location.of('b', 6));
// 		assertThat(actual).isFalse();
// 	}
//
// 	@DisplayName("폰의 대각선 위치에 적이 없는 경우")
// 	@Test
// 	void name2() {
// 		Map<Location, Piece> board = new HashMap<>();
// 		Pawn givenPiece = Pawn.of(Team.BLACK);
// 		board.put(Location.of('a', 7), givenPiece);
//
// 		boolean actual = givenPiece.checkObstacle(board, Location.of('a', 7), Location.of('b', 6));
// 		assertThat(actual).isTrue();
// 	}
//
// 	@DisplayName("폰의 두 칸의 직선 위치로 가는 중 적이 있는 경우")
// 	@Test
// 	void name3() {
// 		Map<Location, Piece> board = new HashMap<>();
// 		Pawn givenPiece = Pawn.of(Team.BLACK);
// 		Pawn counterPiece = Pawn.of(Team.WHITE);
// 		Pawn destinaionPiece = Pawn.of(Team.WHITE);
//
// 		board.put(Location.of('a', 7), givenPiece);
// 		board.put(Location.of('a', 6), counterPiece);
// 		board.put(Location.of('a', 5), destinaionPiece);
//
// 		boolean actual = givenPiece.checkObstacle(board, Location.of('a', 7), Location.of('a', 5));
// 		assertThat(actual).isTrue();
// 	}
//
// 	@DisplayName("폰의 직선 위치에 적이 있는 경우")
// 	@Test
// 	void name4() {
// 		Map<Location, Piece> board = new HashMap<>();
// 		Pawn givenPiece = Pawn.of(Team.BLACK);
// 		Pawn counterPiece = Pawn.of(Team.WHITE);
// 		board.put(Location.of('a', 7), givenPiece);
// 		board.put(Location.of('a', 6), counterPiece);
//
// 		boolean actual = givenPiece.checkObstacle(board, Location.of('a', 7), Location.of('a', 6));
// 		assertThat(actual).isTrue();
// 	}
//
// 	@DisplayName("폰의 직선 위치에 적이 있는 경우")
// 	@Test
// 	void name5() {
// 		Map<Location, Piece> board = new HashMap<>();
// 		Pawn givenPiece = Pawn.of(Team.BLACK);
// 		Pawn destinaionPiece = Pawn.of(Team.WHITE);
// 		board.put(Location.of('a', 7), givenPiece);
// 		board.put(Location.of('a', 5), destinaionPiece);
//
// 		boolean actual = givenPiece.checkObstacle(board, Location.of('a', 7), Location.of('a', 5));
// 		assertThat(actual).isTrue();
// 	}
// }