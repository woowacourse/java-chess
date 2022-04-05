package chess.domain.position;

import static chess.constants.TestConstants.PARAMETERIZED_TEST_NAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DirectionTest {

    @Test
    @DisplayName("Empty에서 pawnDirection을 호출하면 예외가 발생한다.")
    void pawnDirectionThrowException() {
        assertThatThrownBy(() -> Direction.pawnDirection(Color.EMPTY))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("해당 폰의 디렉션은 존재하지 않습니다.");
    }

    @ParameterizedTest(name = PARAMETERIZED_TEST_NAME)
    @CsvSource(value = {"f4, EAST", "b4, WEST", "e5,NORTH_EAST", "e6, NORTH_NORTH_EAST"})
    @DisplayName("두 포지션을 비교하여 방향을 얻는다.")
    void getDirectionByPositions(String targetPostion, Direction expected) {
        Position source = Position.of("d4");
        Position target = Position.of(targetPostion);
        assertThat(Direction.getDirectionByPositions(source, target)).isEqualTo(expected);
    }

    @ParameterizedTest(name = PARAMETERIZED_TEST_NAME)
    @CsvSource(value = {"NORTH, true", "SOUTH, true", "EAST, false", "WEST, false"})
    void isPawnStraigtDirection(Direction direction, boolean expected) {
        assertThat(direction.isPawnStraigtDirection()).isEqualTo(expected);
    }
}
