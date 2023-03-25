package domain.piece;

import static org.junit.jupiter.params.provider.Arguments.arguments;

import domain.Square;
import domain.piece.nonsliding.Knight;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("나이트는")
class KnightTest {

    @DisplayName("상, 하, 좌, 우 방향의 왼쪽, 오른쪽 대각선으로 점프할 수 있다.")
    @ParameterizedTest(name = "{index} : {0} => {1}")
    @MethodSource("parametersProvider1")
    void move_success(Square source, Square destination) {
        Knight knight = new Knight(TeamColor.WHITE);
        List<Square> actual = knight.findRoutes(source, destination);

        Assertions.assertThat(actual).isEqualTo(List.of(destination));
    }

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
    void move_fail(Square source, Square destination) {
        Knight knight = new Knight(TeamColor.WHITE);

        Assertions.assertThatThrownBy(() -> knight.findRoutes(source, destination))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("해당 목적지로 갈 수 없습니다.");
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
