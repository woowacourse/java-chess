package chess.domain.piece.position;

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

    @ParameterizedTest()
    @DisplayName("#calculateDistance() : should return Distance between Positions")
    @MethodSource({"getCasesForCalculateDistance"})
    void calculateDistance(Position from, Position to, Distance expected) {
        //when
        Distance distance = from.calculateDistance(to);
        //then
        assertThat(distance).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("#isNotForward() : should return boolean as to Position 'to' identify forward than 'from'")
    @MethodSource({"getCasesForIsBackward"})
    void isBackward(Position from, Direction teamForwardDirection, boolean expected) {
        Position to = Position.of(1,2);
        assertThat(to.isNotForward(from, teamForwardDirection)).isEqualTo(expected);

    }

    private static Stream<Arguments> getCasesForCalculateDistance() {
        return Stream.of(
                Arguments.of(Position.of(1,1), Position.of(2,2), Distance.of(Math.sqrt(2))),
                Arguments.of(Position.of(4,4), Position.of(5,7), Distance.of(Math.sqrt(10)))
        );
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

    @Test
    void toStringTest() {
        assertThat(Position.of(1,1).toString()).isEqualTo("11");
    }
}
