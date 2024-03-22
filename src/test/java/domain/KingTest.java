package domain;

import fixture.PositionFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.LinkedHashMap;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class KingTest {

    private static Stream<Arguments> movableTargetPosition() {
        return Stream.of(
                Arguments.arguments(PositionFixture.c4()),
                Arguments.arguments(PositionFixture.d5()),
                Arguments.arguments(PositionFixture.c5()),
                Arguments.arguments(PositionFixture.e5())
        );
    }

    private static Stream<Arguments> immovableTargetPosition() {
        return Stream.of(
                Arguments.arguments(PositionFixture.b6()),
                Arguments.arguments(PositionFixture.d6()),
                Arguments.arguments(PositionFixture.b6()),
                Arguments.arguments(PositionFixture.f6())
        );
    }

    @DisplayName("킹은 수직, 수평 또는 대각선 방향으로 한 칸 움직인다.")
    @ParameterizedTest
    @MethodSource("movableTargetPosition")
    void canMoveTest(Position target) {
        King king = new King(Side.BLACK);

        Position current = PositionFixture.d4();

        boolean actual = king.canMove(current, target, new LinkedHashMap<>());

        assertThat(actual).isTrue();
    }

    @DisplayName("킹은 수직, 수평 또는 대각선 방향으로 두 칸 이상 움직일 수 없다.")
    @ParameterizedTest
    @MethodSource("immovableTargetPosition")
    void cantMoveTest(Position target) {
        King king = new King(Side.BLACK);

        Position current = PositionFixture.d4();
        
        boolean actual = king.canMove(current, target, new LinkedHashMap<>());

        assertThat(actual).isFalse();
    }
}
