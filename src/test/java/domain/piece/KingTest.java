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

@DisplayName("킹은")
class KingTest {

    @DisplayName("8방향으로 한칸 갈 수있다")
    @ParameterizedTest(name = "{index} : {0} => {1}")
    @MethodSource("parametersProvider1")
    void move_success(Square src, Square dest) {
        King king = new King(TeamColor.WHITE);
        List<Square> actual = king.findRoutes(src, dest);

        Assertions.assertThat(actual).isEqualTo(List.of(dest));
    }

    static Stream<Arguments> parametersProvider1() {
        return Stream.of(
            arguments(Square.of(3, 3), Square.of(3, 4)),
            arguments(Square.of(3, 3), Square.of(4, 4)),
            arguments(Square.of(3, 3), Square.of(4, 3)),
            arguments(Square.of(3, 3), Square.of(4, 2)),
            arguments(Square.of(3, 3), Square.of(3, 2)),
            arguments(Square.of(3, 3), Square.of(2, 2)),
            arguments(Square.of(3, 3), Square.of(2, 3)),
            arguments(Square.of(3, 3), Square.of(2, 4))
        );
    }

    @DisplayName("한칸 이상 움직일 수 없다.")
    @ParameterizedTest(name = "{index} : {0} !=> {1}")
    @MethodSource("parametersProvider2")
    void move_fail(Square src, Square dest) {
        King king = new King(TeamColor.WHITE);
        List<Square> actual = king.findRoutes(src, dest);

        Assertions.assertThat(actual).isEqualTo(Collections.emptyList());
    }

    static Stream<Arguments> parametersProvider2() {
        return Stream.of(
            arguments(Square.of(3, 3), Square.of(3, 5)),
            arguments(Square.of(3, 3), Square.of(5, 5)),
            arguments(Square.of(3, 3), Square.of(5, 3)),
            arguments(Square.of(3, 3), Square.of(4, 1)),
            arguments(Square.of(3, 3), Square.of(3, 3)),
            arguments(Square.of(3, 3), Square.of(1, 2)),
            arguments(Square.of(3, 3), Square.of(1, 3)),
            arguments(Square.of(3, 3), Square.of(2, 5))
        );
    }
}
