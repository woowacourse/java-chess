package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Objects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PathCheckTest {
	@DisplayName("a1에서 a3 사이에는 a2가 있다.")
	@Test
	void path_a1_a3() {
		assertThat(PathCheck.check(new Position(Column.A, Row.ONE),
			new Position(Column.A, Row.THREE),
			(position) -> Objects.equals(position, new Position(Column.A, Row.TWO)))).isTrue();
	}

	@DisplayName("a1에서 c1 사이에는 b1이 있다.")
	@Test
	void path_a1_c1() {
		assertThat(PathCheck.check(new Position(Column.A, Row.ONE),
			new Position(Column.C, Row.ONE),
			(position) -> Objects.equals(position, new Position(Column.B, Row.ONE)))).isTrue();
	}

	@DisplayName("e1에서 c3 사이에는 d2가 있다.")
	@Test
	void path_e1_c3() {
		assertThat(PathCheck.check(new Position(Column.E, Row.ONE),
			new Position(Column.C, Row.THREE),
			(position) -> Objects.equals(position, new Position(Column.D, Row.TWO)))).isTrue();
	}
}
