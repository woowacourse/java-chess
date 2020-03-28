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
    @DisplayName("#calculateDistance() : should return Distance between Positions")
    void calculateDistance() {
        //given
        int fromX = 1;
        int fromY = 1;
        Position from = Position.of(fromX, fromY);
        int toX = 2;
        int toY = 2;
        Position to = Position.of(toX, toY);
        //when
        Distance distance = from.calculateDistance(to);
        //then
        assertThat(distance).isEqualTo(Distance.of(toX - fromX, toY - fromY));
    }

    @ParameterizedTest
    @DisplayName("#isNotForward() : should return boolean as to Position 'to' is forward than 'from'")
    @MethodSource({"getCasesForIsBackward"})
    void isBackward(Position from, Direction teamForwardDirection, boolean expected) {
        Position to = Position.of(1,2);
        assertThat(to.isNotForward(from, teamForwardDirection)).isEqualTo(expected);

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
