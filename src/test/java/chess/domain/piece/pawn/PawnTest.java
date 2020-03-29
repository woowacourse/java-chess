package chess.domain.piece.pawn;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Team;
import chess.domain.position.Position;

class PawnTest {
	@Test
	@DisplayName("움직인 말이 흰색 말일때 흰색의 형태를 출력하는지 확인합니다.")
	void testToWhiteString() {
		assertThat(MovedPawn.of(Team.WHITE, Position.of("a1")).toString()).isEqualTo("\u2659");
	}

	@Test
	@DisplayName("움직인 말이 검은색 말일때 검은의 형태를 출력하는지 확인합니다.")
	void testToBlackString() {
		assertThat(MovedPawn.of(Team.BLACK, Position.of("a1")).toString()).isEqualTo("\u265f");
	}

	@Test
	@DisplayName("움직이지 않은 말이 흰색 말일때 흰색의 형태를 출력하는지 확인합니다.")
	void testToNotMovedWhiteString() {
		assertThat(NotMovedPawn.of(Team.WHITE, Position.of("a1")).toString()).isEqualTo("\u2659");
	}

	@Test
	@DisplayName("움직이지 않은 말이검은색 말일때 검은의 형태를 출력하는지 확인합니다.")
	void testToNotMovedBlackString() {
		assertThat(NotMovedPawn.of(Team.BLACK, Position.of("a1")).toString()).isEqualTo("\u265f");
	}

}