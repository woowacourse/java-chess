package domain;

import fixture.PositionFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.LinkedHashMap;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {

    private static Stream<Arguments> movableTargetPosition() {
        return Stream.of(
                Arguments.arguments(PositionFixture.b3()),
                Arguments.arguments(PositionFixture.c2())
        );
    }

    private static Stream<Arguments> immovableTargetPosition() {
        return Stream.of(
                Arguments.arguments(PositionFixture.A2()),
                Arguments.arguments(PositionFixture.A3()),
                Arguments.arguments(PositionFixture.b1()),
                Arguments.arguments(PositionFixture.b2()),
                Arguments.arguments(PositionFixture.c1()),
                Arguments.arguments(PositionFixture.c3())
        );
    }

    @DisplayName("나이트는 수평으로 두 칸 수직으로 한 칸, 또는 수직으로 두 칸 수평으로 한 칸 움직인다.")
    @ParameterizedTest
    @MethodSource("movableTargetPosition")
    void canMoveTest(Position target) {
        Knight knight = new Knight(Side.BLACK);
        Position current = PositionFixture.A1();

        boolean actual = knight.isRuleBroken(current, target, new LinkedHashMap<>());

        assertThat(actual).isTrue();
    }

    @DisplayName("나이트는 수평으로 두 칸 수직으로 한 칸, 또는 수직으로 두 칸 수평으로 한 칸을 제외하고 움직일 수 없다.")
    @ParameterizedTest
    @MethodSource("immovableTargetPosition")
    void cantMoveTest(Position target) {
        Knight knight = new Knight(Side.BLACK);
        Position current = PositionFixture.A1();

        boolean actual = knight.isRuleBroken(current, target, new LinkedHashMap<>());

        assertThat(actual).isFalse();
    }
}
