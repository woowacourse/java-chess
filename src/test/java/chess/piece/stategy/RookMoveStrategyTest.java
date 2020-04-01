package chess.piece.stategy;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import chess.board.Location;
import chess.team.Team;

class RookMoveStrategyTest {

	//  ........  8 (rank 8)
	// 	........  7
	// 	........  6
	// 	........  5
	// 	........  4
	// 	.t......  3
	//  ..t.....  2
	// 	r.......  1 (rank 1)
	//
	// 	abcdefgh

	@DisplayName("룩이 이동할 수 없는 경로면 Exception")
	@ParameterizedTest
	@ValueSource(strings = {"b3", "c2"})
	void checkRange(String arongInput) {
		RookMoveStrategy rookMoveStrategy = new RookMoveStrategy(Team.BLACK);
		Location target = Location.of(arongInput);
		assertThatThrownBy(() -> rookMoveStrategy.checkRange(Location.of("a1"), target))
			.isInstanceOf(IllegalArgumentException.class);
	}
}