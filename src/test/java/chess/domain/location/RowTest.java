package chess.domain.location;

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
    void moveBy(int value, Row expected) {
        Row row = Row.SIX;
        assertThat(row.move(value)).isEqualTo(expected);
    }

    @Test
    void moveByException() {
        Row row = Row.SIX;
        assertThatThrownBy(() -> row.move(3)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> row.move(-6)).isInstanceOf(IllegalArgumentException.class);
    }

}