package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PositionTest {

    @ParameterizedTest
    @MethodSource({"getValidCasesForOf"})
    @DisplayName("#of() : should return position")
    void ofSucceed(int x, int y) {
        Position position = Position.of(x, y);
        assertThat(position).isNotNull();
    }

    @ParameterizedTest
    @MethodSource({"getInvalidCasesForOf"})
    @DisplayName("#of() : should throw IllegalArgumentException")
    void ofThrowException(int x, int y) {
        assertThatThrownBy(() -> Position.of(x,y))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    void serialize() {
        Position position = Position.of(1, 1);
        String serialized = position.serialize();
        assertThat(serialized).isEqualTo("a1");
    }

    @Test
    void toStringTest() {
        assertThat(Position.of(1,1).toString()).isEqualTo("11");
    }

    private static Stream<Arguments> getValidCasesForOf() {
        return Stream.of(
                Arguments.of(1,1),
                Arguments.of(8,8)
        );
    }

    private static Stream<Arguments> getInvalidCasesForOf() {
        return Stream.of(
                Arguments.of(0,0),
                Arguments.of(9,9)
        );
    }

    private static Stream<Arguments> getCasesForIsBackward() {
        return Stream.of(
                Arguments.of(Position.of(1,1), Direction.NORTH, false),
                Arguments.of(Position.of(1,3), Direction.NORTH, true)
        );
    }
}
