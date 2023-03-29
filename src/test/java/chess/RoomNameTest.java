package chess;

import chess.domain.RoomName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class RoomNameTest {
    @DisplayName("방 이름은 null일 수 없다.")
    @Test
    void createRoomNameFailByNullTest() {
        assertThatThrownBy(() -> new RoomName(null))
                .isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest(name = "방 이름은 1글자 이상, 10글자 이하여야 한다.")
    @ValueSource(strings = {"", "12345678910"})
    void createRoomNameFailByLengthTest(String name) {
        assertThatThrownBy(() -> new RoomName(name))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "방 이름은 1글자 이상, 10글자 이하여야 한다.")
    @ValueSource(strings = {" ", "123", "0123456789"})
    void createRoomNameSuccessTest(String name) {
        assertDoesNotThrow(() -> new RoomName(name));
    }
}
