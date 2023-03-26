package chess.domain.position;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

	@Test
	@DisplayName("값에 맞는 위치를 반환한다")
	void fileFromValue() {
		// given
		final var file = File.A;
		final var rank = Rank.ONE;
		final var value = "a1";
		final var expected = Position.of(file, rank);

		// when
		final var actual = Position.from(value);

		// then
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	@DisplayName("위치는 파일을 가진다")
	void hasFileInPosition() {
		// given
		final var position = Position.from("a1");
		final var expected = 'a';

		// when
		final var actual = position.fileValue();

		// then
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	@DisplayName("위치는 랭크를 가진다")
	void hasRankInPosition() {
		// given
		final var position = Position.from("a1");
		final var expected = 1;

		// when
		final var actual = position.rankValue();

		// then
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	@DisplayName("file과 rank가 같으면 같은 위치이다")
	void isSamePosition() {
		// given
		final var source = Position.of(File.A, Rank.ONE);
		final var target = Position.of(File.A, Rank.ONE);

		// then
		assertTrue(target.isSame(source));
	}
}
