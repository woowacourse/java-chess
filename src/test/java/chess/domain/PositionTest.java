package chess.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class PositionTest {

	@ParameterizedTest
	@EnumSource(value = Column.class)
	void of_Returns_SameInstance_When_HasEqualValue(Column column) {
		Row row = Row.ONE;
		assertThat(Position.of(column, row)).isEqualTo(Position.of(column, row));
	}

	@Test
	void of_DoesNotThrowException_When_CreatedByString() {
		assertThatCode(() -> Position.of("a1"))
			.doesNotThrowAnyException();
	}

	@Test
	void up_When_Success() {
		Position expected = Position.of("a2");
		Position actual = Position.of("a1").up();

		assertThat(actual).isEqualTo(expected);
	}

	@Test
	void up_When_Fail() {
		assertThatThrownBy(() -> Position.of("a8").up())
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("9 이상의 row 값은 가질수 없습니다.");
	}

	@Test
	void down_When_Success() {
		Position expected = Position.of("a1");
		Position actual = Position.of("a2").down();

		assertThat(actual).isEqualTo(expected);
	}

	@Test
	void down_When_Fail() {
		assertThatThrownBy(() -> Position.of("a1").down())
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("0 이하의 row 값은 가질수 없습니다.");
	}

	@Test
	void right_When_Success() {
		Position expected = Position.of("b1");
		Position actual = Position.of("a1").right();

		assertThat(actual).isEqualTo(expected);
	}

	@Test
	void right_When_Fail() {
		assertThatThrownBy(() -> Position.of("h1").right())
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("9 이상의 column 값은 가질수 없습니다.");
	}

	@Test
	void left_When_Success() {
		Position expected = Position.of("a1");
		Position actual = Position.of("b1").left();

		assertThat(actual).isEqualTo(expected);
	}

	@Test
	void left_When_Fail() {
		assertThatThrownBy(() -> Position.of("a1").left())
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("0 이하의 column 값은 가질수 없습니다.");
	}

	@Test
	void columnGap() {
		Position start = Position.of("a1");
		Position end = Position.of("b1");

		assertThat(start.getColumnGap(end)).isEqualTo(-1);
	}

	@Test
	void rowGap() {
		Position start = Position.of("a1");
		Position end = Position.of("a2");

		assertThat(start.getRowGap(end)).isEqualTo(-1);
	}
}