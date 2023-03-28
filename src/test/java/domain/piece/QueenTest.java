package domain.piece;

import static org.junit.jupiter.params.provider.Arguments.arguments;

import domain.Square;
import domain.piece.sliding.Queen;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("퀸은")
class QueenTest {

    @DisplayName("8방향으로 어디든 갈 수있다")
    @ParameterizedTest(name = "{index} : {0} => {1}")
    @MethodSource("parametersProvider1")
    void move_success(Square source, Square destination) {
        Queen queen = new Queen(TeamColor.WHITE);
        List<Square> actual = queen.findRoutes(source, destination);

        Assertions.assertThat(actual).contains(destination);
        Assertions.assertThat(actual.size()).isEqualTo(7);
    }

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

    @DisplayName("8방향이 아닌 곳은 갈 수 없다.")
    @ParameterizedTest(name = "{index} : {0} !=> {1}")
    @MethodSource("parametersProvider2")
    void move_fail(Square source, Square destination) {
        Queen queen = new Queen(TeamColor.WHITE);

        Assertions.assertThatThrownBy(() -> queen.findRoutes(source, destination))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("해당 방향으로 갈 수 없습니다.");
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
}
