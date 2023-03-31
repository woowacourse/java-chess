package chess.domain.position;

import static chess.domain.position.File.*;
import static chess.domain.position.Rank.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

	@Test
	@DisplayName("값에 맞는 위치를 반환한다")
	void fileFromValue() {
		// given
		final String value = "a1";
		final Position expected = Position.of(A, ONE);

		// when
		final Position actual = Position.from(value);

		// then
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	@DisplayName("위치는 파일을 가진다")
	void hasFileInPosition() {
		// given
		final Position position = Position.from("a1");
		final char expected = 'a';

		// when
		final char actual = position.fileValue();

		// then
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	@DisplayName("위치는 랭크를 가진다")
	void hasRankInPosition() {
		// given
		final Position position = Position.from("a1");
		final int expected = 1;

		// when
		final int actual = position.rankValue();

		// then
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	@DisplayName("file과 rank가 같으면 같은 위치이다")
	void isSamePosition() {
		// given
		final Position source = Position.of(A, ONE);
		final Position target = Position.of(A, ONE);

		// then
		assertTrue(target.isSame(source));
	}
}
