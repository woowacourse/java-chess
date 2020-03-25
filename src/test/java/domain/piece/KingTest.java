package domain.piece;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.piece.position.Position;
import domain.piece.team.Team;

public class KingTest {
	@DisplayName("킹 생성")
	@Test
	void constructor_CreateKing_Success() {
		Assertions.assertThat(new King(Position.of("b1"), Team.WHITE)).isInstanceOf(King.class);
	}
}
