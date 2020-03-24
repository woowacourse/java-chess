package chess.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class PositionTest {

	@ParameterizedTest
	@EnumSource(value = Column.class)
	void of_Returns_SameInstance_When_HasEqualValue(Column column) {
		Row row = Row.ONE;
		assertThat(Position.of(column, row)).isEqualTo(Position.of(column, row));
	}

}