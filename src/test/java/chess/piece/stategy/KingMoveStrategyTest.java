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
// 	..t.t...  4
// 	........  3
// 	..k.....  2
// 	t.......  1 (rank 1)
//
// 	abcdefgh

class KingMoveStrategyTest {

	@DisplayName("왕의 이동 범위가 아니면 Exception")
	@ParameterizedTest
	@ValueSource(strings = {"b4", "e4", "a1"})
	void checkRange(String arongInput) {
		KingMoveStrategy kingMoveStrategy = new KingMoveStrategy(Team.BLACK);
		Location target = Location.of(arongInput);

		assertThatThrownBy(() -> {
			kingMoveStrategy.checkRange(Location.of("c2"), target);
		});
	}
}