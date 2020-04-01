package chess.piece.stategy;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import chess.board.Location;
import chess.team.Team;

class QueenMoveStrategyTest {

	//  ........  8 (rank 8)
	// 	........  7
	// 	........  6
	// 	........  5
	// 	........  4
	// 	.t......  3
	//  ..t.....  2
	// 	q.......  1 (rank 1)
	//
	// 	abcdefgh

	@DisplayName("퀸이 이동할 수 없는 경로면 Exception")
	@ParameterizedTest
	@ValueSource(strings = {"b3", "c2"})
	void checkRange(String arongInput) {
		QueenMoveStrategy queenMoveStrategy = new QueenMoveStrategy(Team.BLACK);
		Location target = Location.of(arongInput);

		assertThatThrownBy(() -> queenMoveStrategy.checkRange(Location.of("a1"), target));
	}
}