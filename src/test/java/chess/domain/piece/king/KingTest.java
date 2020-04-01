package chess.domain.piece.king;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Team;
import chess.domain.position.Position;

class KingTest {
	@Test
	@DisplayName("흰색 말일때 흰색의 형태를 출력하는지 확인합니다.")
	void testToWhiteString() {
		assertThat(King.of(Team.WHITE, Position.of("a1")).toString()).isEqualTo("\u2654");
	}

	@Test
	@DisplayName("검은색 말일때 검은의 형태를 출력하는지 확인합니다.")
	void testToBlackString() {
		assertThat(King.of(Team.BLACK, Position.of("a1")).toString()).isEqualTo("\u265a");
	}
}