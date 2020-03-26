package chess.domain.position;

import chess.domain.Piece.Distance;
import chess.domain.position.Position;
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
}
