package domain.piece;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.piece.position.Position;
import domain.piece.team.Team;

public class QueenTest {
	@DisplayName("퀸 생성")
	@Test
	void constructor_CreateQueen_Success() {
		Assertions.assertThat(new Queen(Position.of("b1"), Team.WHITE)).isInstanceOf(Queen.class);
	}
}
