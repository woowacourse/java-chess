package chess.domain;

import chess.domain.chessPiece.position.File;
import chess.domain.chessPiece.position.Position;
import chess.domain.chessPiece.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PositionTest {
	@Test
	@DisplayName("of 테스트")
	void of() {
		Position result = Position.of(File.F, Rank.SIX);

		assertThat(Position.of("f6")).isEqualTo(result);
	}


}
