package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PositionTest {

    @DisplayName("문자열을 기반으로 Position을 생성한다.")
    @ParameterizedTest
    @ValueSource(strings = {"a1", "a2", "d4", "h8"})
    void 위치를_생성한다(String position) {
        assertDoesNotThrow(() -> new Position(position));
    }

    @DisplayName("전달된 문자열의 길이가 2가 아닌 경우 예외를 던진다.")
    @ParameterizedTest
    @ValueSource(strings = {"a", "abc"})
    void 위치_생성을_실패한다(String position) {
        assertThatThrownBy(() -> new Position(position))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("위치는 두자리여야 합니다.");
    }

    @DisplayName("잘못된 위치 정보인 경우 예외를 던진다.")
    @ParameterizedTest
    @ValueSource(strings = {"i1", "i9"})
    void 잘못된_위치_정보로_생성에_실패한다(String position) {
        assertThatThrownBy(() -> new Position(position))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("방향 정보를 기반으로 이동한 위치를 반환한다.")
    @Test
    void 방향_정보를_기반으로_이동한_위치를_반환한다() {
        Position position = new Position("a1");

        assertThat(position.add(Direction.RIGHT)).isEqualTo(new Position("b1"));
    }
}
