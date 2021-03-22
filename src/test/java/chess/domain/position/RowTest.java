package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RowTest {
    private static Stream<Arguments> rows() {
        return Stream.of(
                Arguments.of(-5, Row.ONE),
                Arguments.of(-4, Row.TWO),
                Arguments.of(0, Row.SIX),
                Arguments.of(2, Row.EIGHT)
        );
    }

    @ParameterizedTest
    @MethodSource("rows")
    @DisplayName("row가 주어진 위치로 잘 이동하는지 확인")
    void moveBy(int value, Row expected) {
        final Row row = Row.SIX;
        assertThat(row.moveBy(value)).isEqualTo(expected);
    }

    @Test
    @DisplayName("체스판을 벗어나는 움직임에서 익셉션을 날리는지 확인")
    void moveByException() {
        final Row row = Row.SIX;
        assertThatThrownBy(() -> row.moveBy(3)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> row.moveBy(-6)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("주어진 두 row 사이의 row를 잘 반환하는지 확인")
    void getBetween() {
        final Row row = Row.SIX;
        assertThat(row.getBetween(Row.THREE)).contains(Row.FOUR, Row.FIVE);
    }
}