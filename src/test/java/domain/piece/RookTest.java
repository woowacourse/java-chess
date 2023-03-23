package domain.piece;

import static org.junit.jupiter.params.provider.Arguments.arguments;

import domain.Square;
import domain.piece.sliding.Queen;
import domain.piece.sliding.Rook;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("룩은")
class RookTest {

    @DisplayName("상, 하, 좌, 우 어디든 갈 수있다")
    @ParameterizedTest(name = "{index} : {0} => {1}")
    @MethodSource("parametersProvider1")
    void move_success(Square src, Square dest, List<Square> expected) {
        Rook rook = new Rook(TeamColor.WHITE);
        List<Square> actual = rook.findRoutes(src, dest);

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> parametersProvider1() {
        return Stream.of(
            arguments(Square.of(3, 3), Square.of(3, 8),
                List.of(
                    Square.of(3, 4),
                    Square.of(3, 5),
                    Square.of(3, 6),
                    Square.of(3, 7),
                    Square.of(3, 8))),

            arguments(Square.of(3, 3), Square.of(8, 3),
                List.of(
                    Square.of(4, 3),
                    Square.of(5, 3),
                    Square.of(6, 3),
                    Square.of(7, 3),
                    Square.of(8, 3))),

            arguments(Square.of(3, 3), Square.of(3, 1),
                List.of(
                    Square.of(3, 2),
                    Square.of(3, 1))),

            arguments(Square.of(3, 3), Square.of(1, 3),
                List.of(
                    Square.of(2, 3),
                    Square.of(1, 3)))
        );
    }

    @DisplayName("상, 하, 좌, 우 방향이 아니면 갈 수 없다.")
    @ParameterizedTest(name = "{index} : {0} !=> {1}")
    @MethodSource("parametersProvider2")
    void move_fail(Square src, Square dest) {
        Queen queen = new Queen(TeamColor.WHITE);

        Assertions.assertThatThrownBy(() -> queen.findRoutes(src, dest))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("해당 방향으로 갈 수 없습니다.");
    }

    static Stream<Arguments> parametersProvider2() {
        return Stream.of(
            arguments(Square.of(3, 3), Square.of(2, 1)),
            arguments(Square.of(3, 3), Square.of(8, 4)),
            arguments(Square.of(3, 3), Square.of(1, 4)),
            arguments(Square.of(3, 3), Square.of(2, 6))
        );
    }
}
