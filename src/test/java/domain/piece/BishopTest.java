package domain.piece;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.piece.position.Position;
import domain.piece.team.Team;

public class BishopTest {
	@DisplayName("비숍 생성")
	@Test
	void constructor_CreateBishop_Success() {
		Assertions.assertThat(new Bishop(Position.of("b1"), Team.WHITE)).isInstanceOf(Bishop.class);
	}
}
