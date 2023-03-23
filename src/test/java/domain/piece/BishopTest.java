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

@DisplayName("비숍은")
class BishopTest {

    @DisplayName("대각선 어디든 갈 수있다")
    @ParameterizedTest(name = "{index} : {0} => {1}")
    @MethodSource("parametersProvider1")
    void move_success(Square src, Square dest, List<Square> expected) {
        Bishop bishop = new Bishop(TeamColor.WHITE);
        List<Square> actual = bishop.findRoutes(src, dest);

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> parametersProvider1() {
        return Stream.of(
            arguments(Square.of(3, 3), Square.of(8, 8),
                List.of(
                    Square.of(4, 4),
                    Square.of(5, 5),
                    Square.of(6, 6),
                    Square.of(7, 7),
                    Square.of(8, 8))),

            arguments(Square.of(3, 3), Square.of(5, 1),
                List.of(
                    Square.of(4, 2),
                    Square.of(5, 1))),

            arguments(Square.of(3, 3), Square.of(1, 1),
                List.of(
                    Square.of(2, 2),
                    Square.of(1, 1))),

            arguments(Square.of(3, 3), Square.of(1, 5),
                List.of(
                    Square.of(2, 4),
                    Square.of(1, 5)))
        );
    }

    @DisplayName("대각선 방향이 아니면 갈 수 없다.")
    @ParameterizedTest(name = "{index} : {0} !=> {1}")
    @MethodSource("parametersProvider2")
    void move_fail(Square src, Square dest) {
        Bishop bishop = new Bishop(TeamColor.WHITE);
        List<Square> actual = bishop.findRoutes(src, dest);

        Assertions.assertThat(actual).isEqualTo(Collections.emptyList());
    }

    static Stream<Arguments> parametersProvider2() {
        return Stream.of(
            arguments(Square.of(3, 3), Square.of(3, 8)),
            arguments(Square.of(3, 3), Square.of(8, 3)),
            arguments(Square.of(3, 3), Square.of(3, 1)),
            arguments(Square.of(3, 3), Square.of(1, 3))
        );
    }
}
