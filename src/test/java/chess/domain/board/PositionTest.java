package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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

    @ParameterizedTest
    @DisplayName("목표 지점으로 가는 경로 중, 자신과 가장 가까운 위치를 반환한다.")
    @CsvSource({"1c, 1b", "3a, 2a", "3c, 2b"})
    void nextPosition(final String targetPositionValue, final String expectedValue) {
        // given
        final Position position = Position.from("1a");
        final Position targetPosition = Position.from(targetPositionValue);
        final Position expected = Position.from(expectedValue);
        // when
        final Position actual = position.nextPosition(targetPosition);
        // then
        assertThat(actual).isEqualTo(expected);
    }
}
