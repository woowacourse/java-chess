package chess.domain.piece.queen;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Team;
import chess.domain.position.Position;

class QueenTest {
	@Test
	@DisplayName("흰색 말일때 흰색의 형태를 출력하는지 확인합니다.")
	void testToWhiteString() {
		assertThat(Queen.of(Team.WHITE, Position.of("a1")).toString()).isEqualTo("\u2655");
	}

	@Test
	@DisplayName("검은색 말일때 검은의 형태를 출력하는지 확인합니다.")
	void testToBlackString() {
		assertThat(Queen.of(Team.BLACK, Position.of("a1")).toString()).isEqualTo("\u265b");
	}
}