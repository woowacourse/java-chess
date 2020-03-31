package chess.piece;

// class KingTest {
//
// 	@Test
// 	void canMove() {
// 		King king = King.of(Team.BLACK);
// 		Location now = Location.of('e', 8);
//
// 		Location after = Location.of('e', 7);
// 		boolean actual = king.checkRange(now, after);
// 		assertThat(actual).isTrue();
//
// 		Location cantAfter = Location.of('c', 6);
// 		boolean cantActual = king.checkRange(now, cantAfter);
// 		assertThat(cantActual).isFalse();
// 	}
// }