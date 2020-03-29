package chess.domain.piece.bishop;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Team;
import chess.domain.position.Position;

class BishopTest {
	@Test
	@DisplayName("흰색 말일때 흰색 말을 출력하는지 확인합니다.")
	void testToWhiteString() {
		assertThat(Bishop.of(Team.WHITE, Position.of("a1")).toString()).isEqualTo("\u2657");
	}

	@Test
	@DisplayName("검은색 말일때 검은 말을 출력하는지 확인합니다.")
	void testToBlackString() {
		assertThat(Bishop.of(Team.BLACK, Position.of("a1")).toString()).isEqualTo("\u265d");
	}
}