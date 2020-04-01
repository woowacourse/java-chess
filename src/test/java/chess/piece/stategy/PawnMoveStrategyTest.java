package chess.piece.stategy;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import chess.board.Location;
import chess.team.Team;

class PawnMoveStrategyTest {
	//  tt......  8 (rank 8)
	// 	pt......  7
	// 	........  6
	// 	........  5
	// 	........  4
	// 	........  3
	// 	........  2
	// 	........  1 (rank 1)
	//
	// 	abcdefgh
	@DisplayName("블랙 폰의 범위가 아닌경우 Exception")
	@ParameterizedTest
	@ValueSource(strings = {"a8", "b8", "b7"})
	void checkRange(String arongInput) {
		PawnMoveStrategy pawnMoveStrategy = new PawnMoveStrategy(Team.BLACK);
		Location target = Location.of(arongInput);
		assertThatThrownBy(() -> pawnMoveStrategy.checkRange(Location.of("a7"), target))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("유효하지 않는 범위를 입력했습니다.");

	}

	//  ........  8 (rank 8)
	// 	........  7
	// 	........  6
	// 	........  5
	// 	........  4
	// 	........  3
	// 	K.......  2
	// 	p.......  1 (rank 1)
	//
	// 	abcdefgh
	@DisplayName("폰 직진 선상에 적이 있으면 Exception")
	@Test
	void checkStrategy() {
		PawnMoveStrategy pawnMoveStrategy = new PawnMoveStrategy(Team.WHITE);
		assertThatThrownBy(() -> pawnMoveStrategy.checkStrategy(Location.of("a1"), Location.of("a2"), true))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("폰의 직선 이동은 적이 없어야 가능합니다.");
	}

	//  ........  8 (rank 8)
	// 	........  7
	// 	........  6
	// 	........  5
	// 	........  4
	// 	........  3
	//  ........  2
	// 	p.......  1 (rank 1)
	//
	// 	abcdefgh
	@DisplayName("폰 대각선 선상에 적이 없으면 Exception")
	@Test
	void checkStrategy2() {
		PawnMoveStrategy pawnMoveStrategy = new PawnMoveStrategy(Team.WHITE);
		assertThatThrownBy(() -> pawnMoveStrategy.checkStrategy(Location.of("a1"), Location.of("b2"), false))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("폰의 대각선 이동은 적이 있어야 가능합니다.");
	}

	//  ........  8 (rank 8)
	// 	........  7
	// 	........  6
	// 	........  5
	// 	........  4
	// 	t.......  3
	//  ........  2
	// 	p.......  1 (rank 1)
	//
	// 	abcdefgh
	@DisplayName("폰이 초기위치가 아닐때 2칸 전진하면 Exception")
	@Test
	void checkStrategy3() {
		PawnMoveStrategy pawnMoveStrategy = new PawnMoveStrategy(Team.WHITE);
		assertThatThrownBy(() -> pawnMoveStrategy.checkStrategy(Location.of("a1"), Location.of("a3"), false))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("폰은 초기위치가 아니면 2칸 전진할 수 없습니다.");
	}
}