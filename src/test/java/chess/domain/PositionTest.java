package chess.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.piece.Position;

public class PositionTest {
	@Test
	void constructor() {
		assertThat(Position.of(0, 0)).isInstanceOf(Position.class);
	}

	@Test
	void equals() {
		assertThat(Position.of(0, 0)).isEqualTo(Position.of(0, 0));
	}

	@Test
	@DisplayName("문자열을 넣었을 때 정상적인 위치를 반환")
	void from() {
		assertThat(Position.from("a1")).isEqualTo(Position.of(0, 0));
		assertThat(Position.from("h8")).isEqualTo(Position.of(7, 7));
	}

	@Test
	@DisplayName("대문자를 넣었을 때 정상적인 위치를 반환")
	void from_uppercase() {
		assertThat(Position.from("A1")).isEqualTo(Position.from("a1"));
		assertThat(Position.from("H8")).isEqualTo(Position.from("H8"));
	}

	@Test
	@DisplayName("범위를 벗어나는 위치에 대해 예외 발생")
	void from_invalid_input() {
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> Position.from("i1"));
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> Position.from("i0"));
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> Position.from("a0"));
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> Position.from("h9"));
	}
}
