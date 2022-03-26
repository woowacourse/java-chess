package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class DirectionTest {

    @ParameterizedTest
    @MethodSource("providePositionDirection")
    @DisplayName("출발 Position에서 목적지 Position으로 가는 방향을 반환하여 확인한다.")
    void checkDirection(Position fromPosition,Position toPosition,Direction direction) {
        assertThat(Direction.judge(fromPosition,toPosition)).isEqualTo(direction);
    }

    private static Stream<Arguments> providePositionDirection() {
        return Stream.of(
            Arguments.of(Position.valueOf(Abscissa.a, Ordinate.ONE),
                Position.valueOf(Abscissa.b, Ordinate.ONE), Direction.RIGHT),
            Arguments.of(Position.valueOf(Abscissa.b, Ordinate.ONE),
                Position.valueOf(Abscissa.a, Ordinate.ONE), Direction.LEFT),
            Arguments.of(Position.valueOf(Abscissa.a, Ordinate.ONE),
                Position.valueOf(Abscissa.a, Ordinate.TWO), Direction.UP),
            Arguments.of(Position.valueOf(Abscissa.a, Ordinate.TWO),
                Position.valueOf(Abscissa.a, Ordinate.ONE), Direction.DOWN),
            Arguments.of(Position.valueOf(Abscissa.a, Ordinate.ONE),
                Position.valueOf(Abscissa.b, Ordinate.TWO), Direction.RIGHTUP),
            Arguments.of(Position.valueOf(Abscissa.a, Ordinate.TWO),
                Position.valueOf(Abscissa.b, Ordinate.ONE), Direction.RIGHTDOWN),
            Arguments.of(Position.valueOf(Abscissa.b, Ordinate.ONE),
                Position.valueOf(Abscissa.a, Ordinate.TWO), Direction.LEFTUP),
            Arguments.of(Position.valueOf(Abscissa.b, Ordinate.TWO),
                Position.valueOf(Abscissa.a, Ordinate.ONE), Direction.LEFTDOWN));
    }
}
