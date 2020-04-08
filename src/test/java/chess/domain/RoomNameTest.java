package chess.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RoomNameTest {
	@Test
	void constructor() {
		assertThat(new RoomName("room")).isInstanceOf(RoomName.class);
	}

	@DisplayName("방 이름이 10글자 초과인 경우 예외 발생")
	@Test
	void validateLength() {
		assertThatThrownBy(() -> new RoomName("12345678901"))
				.isInstanceOf(IllegalArgumentException.class);
	}
}