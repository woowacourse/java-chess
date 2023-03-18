package chess.domain.position;

import static chess.domain.move.Direction.LEFT;
import static chess.domain.move.Direction.UP;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.domain.move.Direction;
import chess.domain.move.Directions;

public class PositionTest {

    @DisplayName("방향으로 이동할 수 있다")
    @ParameterizedTest
    @CsvSource({"RIGHT,C,TWO", "UP,B,THREE", "LEFT,A,TWO", "DOWN,B,ONE"})
    void move(Direction direction, File file, Rank rank) {
        Position position = new Position(File.B, Rank.TWO);

        assertThat(position.move(direction))
                .isEqualTo(new Position(file, rank));
    }

    @DisplayName("다른 위치까지의 수평 방향들을 알 수 있다")
    @Test
    void getHorizontalDirectionsToOtherPosition() {
        Position position = new Position(File.G, Rank.ONE);
        Position other = new Position(File.D, Rank.FOUR);

        assertThat(position.getHorizontalDirectionsTo(other)).isEqualTo(Directions.of(LEFT, LEFT, LEFT));
    }

    @DisplayName("다른 위치까지의 수직 방향들을 알 수 있다")
    @Test
    void getVerticalDirectionsToOtherPosition() {
        Position position = new Position(File.G, Rank.ONE);
        Position other = new Position(File.D, Rank.FOUR);

        assertThat(position.getVerticalDirectionsTo(other)).isEqualTo(Directions.of(UP, UP, UP));
    }
}
