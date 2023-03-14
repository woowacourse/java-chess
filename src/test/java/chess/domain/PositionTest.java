package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PositionTest {

    @DisplayName("방향으로 이동할 수 있다")
    @ParameterizedTest
    @CsvSource({"RIGHT,C,TWO", "UP,B,THREE", "LEFT,A,TWO", "DOWN,B,ONE"})
    void move(Direction direction, File file, Rank rank) {
        Position position = new Position(File.B, Rank.TWO);

        assertThat(position.move(direction))
            .isEqualTo(new Position(file, rank));
    }
}
