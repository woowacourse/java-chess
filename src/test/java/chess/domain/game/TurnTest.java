package chess.domain.game;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.piece.Color;

class TurnTest {
	@Test
	@DisplayName("새로운 턴 반환")
	void next() {
		assertThat(Turn.from(Color.WHITE).next()).isEqualTo(Turn.from(Color.BLACK));
	}
}
