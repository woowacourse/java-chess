package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import domain.piece.Direction;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class SquareTest {

    private static final Square C2 = Square.of(ChessColumn.C, Rank.TWO);
    private static final Square C5 = Square.of(ChessColumn.C, Rank.FIVE);
    private static final Square E8 = Square.of(ChessColumn.E, Rank.EIGHT);
    private static final Square A5 = Square.of(ChessColumn.A, Rank.FIVE);
    private static final Square F6 = Square.of(ChessColumn.F, Rank.SIX);

    static Stream<Arguments> parametersProvider1() {
        return Stream.of(
            arguments(C2, C5, Direction.of(0, 3)),
            arguments(C2, E8, Direction.of(2, 6)),
            arguments(C2, A5, Direction.of(-2, 3)),
            arguments(C2, F6, Direction.of(3, 4))
        );
    }

    static Stream<Arguments> parametersProvider2() {
        return Stream.of(
            arguments(C2, Direction.of(0, 3), C5),
            arguments(C2, Direction.of(2, 6), E8),
            arguments(C2, Direction.of(-2, 3), A5),
            arguments(C2, Direction.of(3, 4), F6)
        );
    }

    static Stream<Arguments> parametersProvider3() {
        return Stream.of(
            arguments(C2, Direction.of(-3, 3)),
            arguments(C2, Direction.of(2, 7)),
            arguments(A5, Direction.of(-1, -1)),
            arguments(A5, Direction.of(8, 2))
        );
    }

    @ParameterizedTest(name = "{index} : 두 Square의 벡터를 구한다")
    @MethodSource("parametersProvider1")
    void calculateVector(Square source, Square destination, Direction expected) {
        Direction actual = destination.calculateVector(source);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest(name = "{index} : next_success")
    @MethodSource("parametersProvider2")
    void next_success(Square source, Direction vector, Square expected) {
        Square actual = source.add(vector);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest(name = "{index} : next_fail")
    @MethodSource("parametersProvider3")
    void next_fail(Square square, Direction vector) {
        assertThatThrownBy(() -> square.add(vector))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("체스판을 벗어날 수 없습니다.");
    }
}
