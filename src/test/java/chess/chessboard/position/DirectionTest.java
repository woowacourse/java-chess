package chess.chessboard.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.chessboard.position.Direction.EAST;
import static chess.chessboard.position.Direction.NORTH;
import static org.assertj.core.api.Assertions.assertThat;

class DirectionTest {

    @DisplayName("보드판이 아닌 범위로 이동하려고 하면 true를 반환한다.")
    @Test
    void isMovablePosition_true() {
        final boolean actual = NORTH.isMovablePosition(Rank.FIVE, File.D);
        assertThat(actual).isTrue();
    }

    @DisplayName("보드판이 아닌 범위로 이동하려고 하면 false를 반환한다.")
    @Test
    void isMovablePosition_false_north() {
        final boolean actual = NORTH.isMovablePosition(Rank.EIGHT, File.D);
        assertThat(actual).isFalse();
    }

    @DisplayName("보드판이 아닌 범위로 이동하려고 하면 false를 반환한다.")
    @Test
    void isMovablePosition_false_east() {
        final boolean actual = EAST.isMovablePosition(Rank.FOUR, File.H);
        assertThat(actual).isFalse();
    }

    @Test
    void createMovablePosition() {
        final Position position = Position.of(Rank.FOUR, File.D);
        final Position movedPosition = NORTH.createMovablePosition(Rank.THREE, File.D);

        assertThat(movedPosition).isEqualTo(position);
    }
}