package chess.piece.stategy;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import chess.board.Location;
import chess.team.Team;

//  ........  8 (rank 8)
// 	........  7
// 	........  6
// 	........  5
// 	........  4
// 	..t.....  3
// 	.tbt....  2
// 	..t.....  1 (rank 1)
//
// 	abcdefgh

class BishopMoveStrategyTest {

	@DisplayName("비숍이 갈 수 없는 범위면 Exception")
	@ParameterizedTest
	@ValueSource(strings = {"b2", "c1", "c3", "d2"})
	void checkRange(String arongInput) {
		BishopMoveStrategy bishopMoveStrategy = new BishopMoveStrategy(Team.BLACK);
		Location target = Location.of(arongInput);
		assertThatThrownBy(() -> {
			bishopMoveStrategy.checkRange(Location.of("c2"), target);
		}).isInstanceOf(IllegalArgumentException.class);
	}
}