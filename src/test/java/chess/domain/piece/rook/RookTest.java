package chess.domain.piece.rook;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Team;
import chess.domain.position.Position;

class RookTest {
	@Test
	@DisplayName("흰색 말일때 흰색의 형태를 출력하는지 확인합니다.")
	void testToWhiteString() {
		assertThat(Rook.of(Team.WHITE, Position.of("a1")).toString()).isEqualTo("\u2656");
	}

	@Test
	@DisplayName("검은색 말일때 검은의 형태를 출력하는지 확인합니다.")
	void testToBlackString() {
		assertThat(Rook.of(Team.BLACK, Position.of("a1")).toString()).isEqualTo("\u265c");
	}
}