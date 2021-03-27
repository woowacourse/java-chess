package chess;

import chess.domain.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PointTest {
    @DisplayName("좌표 생성")
    @Test
    void create() {
        assertDoesNotThrow(
                () -> Point.of("a8")
        );
        assertDoesNotThrow(
                () -> Point.of("h1")
        );
    }

    @DisplayName("범위 밖 좌표 생성 시 예외 처리")
    @ParameterizedTest
    @ValueSource(strings = {"i0", "i8", "h0", "h9"})
    void checkIndexOutOfRange(String value) {
        assertThatThrownBy(() -> Point.of(value)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("인덱스로 좌표 생성")
    @Test
    void createWithIndex() {
        assertDoesNotThrow(
                () -> Point.of(0, 7)
        );
        assertDoesNotThrow(
                () -> Point.of(7, 0)
        );
    }

    @DisplayName("인덱스로 범위 밖 좌표 생성 시 예외 처리")
    @Test
    void checkValueOfRange() {
        assertThatThrownBy(() -> Point.of(-1, -1)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Point.of(0, 8)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Point.of(8, 0)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Point.of(8, 8)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("위치 간 거리 계산 확인")
    @Test
    void calculateDistance() {
        assertThat(Point.of("a1").calculateDistance(Point.of("a2"))).isEqualTo(1);
        assertThat(Point.of("a1").calculateDistance(Point.of("b2"))).isEqualTo(2);
        assertThat(Point.of("a1").calculateDistance(Point.of("h8"))).isEqualTo(98);
    }
}