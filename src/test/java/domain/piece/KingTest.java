package domain.piece;

import static org.junit.jupiter.params.provider.Arguments.arguments;

import domain.Square;
import domain.piece.nonsliding.King;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("킹은")
class KingTest {

    @DisplayName("8방향으로 한칸 갈 수있다")
    @ParameterizedTest(name = "{index} : {0} => {1}")
    @MethodSource("parametersProvider1")
    void move_success(Square source, Square destination) {
        King king = new King(TeamColor.WHITE);
        List<Square> actual = king.findRoutes(source, destination);

        Assertions.assertThat(actual).isEqualTo(List.of(destination));
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
    void move_fail(Square source, Square destination, String expectedMessage) {
        King king = new King(TeamColor.WHITE);
        Assertions.assertThatThrownBy(() -> king.findRoutes(source, destination))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(expectedMessage);
    }

    static Stream<Arguments> parametersProvider2() {
        return Stream.of(
            arguments(Square.of(3, 3), Square.of(3, 5), "해당 목적지로 갈 수 없습니다."),
            arguments(Square.of(3, 3), Square.of(5, 5), "해당 목적지로 갈 수 없습니다."),
            arguments(Square.of(3, 3), Square.of(5, 3), "해당 목적지로 갈 수 없습니다."),
            arguments(Square.of(3, 3), Square.of(3, 3), "해당 목적지로 갈 수 없습니다."),
            arguments(Square.of(3, 3), Square.of(1, 3), "해당 목적지로 갈 수 없습니다."),
            arguments(Square.of(3, 3), Square.of(4, 1), "해당 목적지로 갈 수 없습니다."),
            arguments(Square.of(3, 3), Square.of(1, 2), "해당 목적지로 갈 수 없습니다."),
            arguments(Square.of(3, 3), Square.of(2, 5), "해당 목적지로 갈 수 없습니다.")
        );
    }
}
