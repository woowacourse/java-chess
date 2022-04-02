package chess.domain.board.position;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PositionTest {

    @ParameterizedTest
    @ValueSource(strings = {"", "1aa", "a"})
    @DisplayName("유효하지 않은 값으로 생성하려 할 때 예외를 발생시킨다.")
    void createException(final String invalidValue) {
        //when, then
        assertThatThrownBy(() -> Position.from(invalidValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("위치 정보가 유효하지 않습니다.");
    }
}
