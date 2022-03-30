package chess.domain.game.state.position;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class FileTest {

    @ParameterizedTest(name = "{index}: {3}")
    @MethodSource("validParameters")
    @DisplayName("가로로 이동이 가능해야 한다.")
    void moveToSameRank(File sourceFile, File targetFile, Direction direction, String testName) {
        Position target = Position.of(sourceFile.findNext(direction.getFile()), Rank.One);
        assertThat(target).isEqualTo(Position.of(targetFile, Rank.One));
    }

    private static Stream<Arguments> validParameters() {
        return Stream.of(
            Arguments.of(File.a, File.b, Direction.Right, "오른쪽으로 이동"),
            Arguments.of(File.b, File.a, Direction.Left, "왼쪽으로 이동")
        );
    }
}