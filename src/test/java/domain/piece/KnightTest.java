package domain.piece;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import domain.Square;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("나이트는 ")
class KnightTest {
    @DisplayName("상, 하, 좌, 우 방향의 왼쪽, 오른쪽 대각선으로 점프할 수 있다.")
    @ParameterizedTest(name = "{index} : {0} => {1}")
    @MethodSource("parametersProvider1")
    void move_success(Square src, Square dest) {
        Knight knight = new Knight(TeamColor.WHITE);
        List<Square> actual = knight.findRoutes(src, dest);

        Assertions.assertThat(actual).isEqualTo(List.of(dest));
    }

    // (1,8) (2,8) (3,8) (4,8) (5,8) (6,8) (7,8) (8,8)
    // (1,7) (2,7) (3,7) (4,7) (5,7) (6,7) (7,7) (8,7)
    // (1,6) (2,6) (3,6) (4,6) (5,6) (6,6) (7,6) (8,6)
    // (1,5) (2,5) (3,5) (4,5) (5,5) (6,5) (7,5) (8,5)
    // (1,4) (2,4) (3,4) (4,4) (5,4) (6,4) (7,4) (8,4)
    // (1,3) (2,3) <3,3> (4,3) (5,3) (6,3) (7,3) (8,3)
    // (1,2) (2,2) (3,2) (4,2) (5,2) (6,2) (7,2) (8,2)
    // (1,1) (2,1) (3,1) (4,1) (5,1) (6,1) (7,1) (8,1)

    static Stream<Arguments> parametersProvider1() {
        return Stream.of(
            arguments(Square.of(3, 3), Square.of(4, 5)),
            arguments(Square.of(3, 3), Square.of(5, 4)),
            arguments(Square.of(3, 3), Square.of(5, 2)),
            arguments(Square.of(3, 3), Square.of(4, 1)),
            arguments(Square.of(3, 3), Square.of(2, 1)),
            arguments(Square.of(3, 3), Square.of(1, 2)),
            arguments(Square.of(3, 3), Square.of(1, 4)),
            arguments(Square.of(3, 3), Square.of(2, 5))
        );
    }

    @DisplayName("상, 하, 좌, 우 방향의 왼쪽, 오른쪽 대각선외에는 점프하지 못한다.")
    @ParameterizedTest(name = "{index} : {0} !=> {1}")
    @MethodSource("parametersProvider2")
    void move_fail(Square src, Square dest) {
        Knight knight = new Knight(TeamColor.WHITE);
        List<Square> actual = knight.findRoutes(src, dest);

        Assertions.assertThat(actual).isEqualTo(Collections.emptyList());
    }

    static Stream<Arguments> parametersProvider2() {
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
}
