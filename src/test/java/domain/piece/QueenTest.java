package domain.piece;

import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import domain.Square;

@DisplayName("퀸은")
class QueenTest {

    static Stream<Arguments> parametersProvider1() {
        return Stream.of(
            arguments(Square.of(1, 1), Square.of(1, 8)),
            arguments(Square.of(1, 1), Square.of(8, 8)),
            arguments(Square.of(1, 8), Square.of(8, 1)),
            arguments(Square.of(1, 8), Square.of(1, 1)),
            arguments(Square.of(8, 8), Square.of(8, 1)),
            arguments(Square.of(8, 8), Square.of(1, 1)),
            arguments(Square.of(8, 1), Square.of(1, 1)),
            arguments(Square.of(8, 1), Square.of(1, 8))
        );
    }

    static Stream<Arguments> parametersProvider2() {
        return Stream.of(
            arguments(Square.of(3, 3), Square.of(4, 1)),
            arguments(Square.of(3, 3), Square.of(2, 1)),
            arguments(Square.of(3, 3), Square.of(8, 7)),
            arguments(Square.of(3, 3), Square.of(8, 4)),
            arguments(Square.of(3, 3), Square.of(1, 2)),
            arguments(Square.of(3, 3), Square.of(1, 4)),
            arguments(Square.of(3, 3), Square.of(1, 6)),
            arguments(Square.of(3, 3), Square.of(2, 6))
        );
    }

    @DisplayName("8방향으로 어디든 갈 수있다")
    @ParameterizedTest(name = "{index} : {0} => {1}")
    @MethodSource("parametersProvider1")
    void move_success(Square src, Square dest) {
        Queen queen = new Queen(TeamColor.WHITE);
        List<Square> actual = queen.findRoutes(src, dest);

        Assertions.assertThat(actual).contains(dest);
        Assertions.assertThat(actual.size()).isEqualTo(7);
    }

    @DisplayName("8방향이 아닌 곳은 갈 수 없다.")
    @ParameterizedTest(name = "{index} : {0} !=> {1}")
    @MethodSource("parametersProvider2")
    void move_fail(Square src, Square dest) {
        Queen queen = new Queen(TeamColor.WHITE);
        List<Square> actual = queen.findRoutes(src, dest);

        Assertions.assertThat(actual).isEqualTo(Collections.emptyList());
    }
}
