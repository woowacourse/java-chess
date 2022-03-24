package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class DirectionTest {

    private static Stream<Arguments> generateDirection() {
        return Stream.of(
                Arguments.of("c4", "e4", Direction.RIGHT),
                Arguments.of("c4", "b4", Direction.LEFT),
                Arguments.of("c4", "c7", Direction.TOP),
                Arguments.of("c4", "c3", Direction.BOTTOM),
                Arguments.of("c4", "e6", Direction.TOP_RIGHT),
                Arguments.of("c4", "a6", Direction.TOP_LEFT),
                Arguments.of("c4", "e2", Direction.BOTTOM_RIGHT),
                Arguments.of("c4", "a2", Direction.BOTTOM_LEFT)
        );
    }

    @DisplayName("source 위치와 target 위치를 기반으로 이동할 방향을 반환한다.")
    @ParameterizedTest
    @MethodSource("generateDirection")
    void source와_target_위치에_따른_방향_정보를_반환한다(String source, String target, Direction expected) {
        Position sourcePosition = new Position(source);
        Position targetPosition = new Position(target);

        assertThat(Direction.of(sourcePosition, targetPosition)).isEqualTo(expected);
    }
}
