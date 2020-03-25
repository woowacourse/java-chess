package chess.position;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class FileTest {
	@DisplayName("File 객체값이 a-h 사이인지 테스트")
	@ParameterizedTest
	@ValueSource(strings = {"n", "i", "z"})
	void invalidConstructTest(String name) {
		assertThatThrownBy(() -> File.of(name)).isInstanceOf(IllegalArgumentException.class)
			.hasMessage("올바른 열값이 아닙니다.");
	}

	@DisplayName("File 객체값이 a-h 사이인지 테스트")
	@ParameterizedTest
	@ValueSource(strings = {"a", "h"})
	void constructTest(String name) {
		assertThat(File.of(name)).isInstanceOf(File.class);
	}

	@DisplayName("displayName")
	@Test
	void valuesBetween() {
	}
}