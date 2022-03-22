import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;

public class PositionTest {
	@DisplayName("생성 확인")
	@Test
	void create() {
		// then
		assertThat(new Position(Row.A, Column.ONE)).isNotNull();
	}
}
