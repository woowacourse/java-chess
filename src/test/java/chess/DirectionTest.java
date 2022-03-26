package chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.Direction.*;
import static org.assertj.core.api.Assertions.assertThat;

class DirectionTest {

    @DisplayName("보드판이 아닌 범위로 이동하려고 하면 true를 반환한다.")
    @Test
    void isMovablePosition_true() {
        boolean actual = NORTH.isMovablePosition(Rank.FIVE, File.D);
        assertThat(actual).isTrue();
    }

    @DisplayName("보드판이 아닌 범위로 이동하려고 하면 false를 반환한다.")
    @Test
    void isMovablePosition_false_north() {
        boolean actual = NORTH.isMovablePosition(Rank.EIGHT, File.D);
        assertThat(actual).isFalse();
    }

    @DisplayName("보드판이 아닌 범위로 이동하려고 하면 false를 반환한다.")
    @Test
    void isMovablePosition_false_east() {
        boolean actual = EAST.isMovablePosition(Rank.FOUR, File.H);
        assertThat(actual).isFalse();
    }

    @Test
    void createMovablePosition() {
        Position position = new Position(Rank.FOUR, File.D);
        Position movedPosition = NORTH.createMovablePosition(Rank.THREE, File.D);

        assertThat(movedPosition).isEqualTo(position);
    }
}