package factory;

import domain.piece.attribute.point.Direction;
import domain.piece.attribute.point.File;
import domain.piece.attribute.point.Point;
import domain.piece.attribute.point.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.DirectionUtil;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DirectionUtilTest {
    @Test
    @DisplayName("두 좌표 위치를 토대로 Up 방향인지 판단한다")
    void select_up_direction_by_points_position() {
        final Point point1 = new Point(File.A, Rank.ONE);
        final Point point2 = new Point(File.A, Rank.TWO);

        final Direction direction = DirectionUtil.determineDirection(point1, point2);

        assertThat(direction).isEqualTo(Direction.UP);
    }


    @Test
    @DisplayName("두 좌표 위치를 토대로 Down 방향인지 판단한다")
    void select_down_direction_by_points_position() {
        final Point point1 = new Point(File.A, Rank.TWO);
        final Point point2 = new Point(File.A, Rank.ONE);

        final Direction direction = DirectionUtil.determineDirection(point1, point2);

        assertThat(direction).isEqualTo(Direction.DOWN);
    }

    @Test
    @DisplayName("두 좌표 위치를 토대로 Left 방향인지 판단한다")
    void select_left_direction_by_points_position() {
        final Point point1 = new Point(File.B, Rank.TWO);
        final Point point2 = new Point(File.A, Rank.TWO);

        final Direction direction = DirectionUtil.determineDirection(point1, point2);

        assertThat(direction).isEqualTo(Direction.LEFT);
    }

    @Test
    @DisplayName("두 좌표 위치를 토대로 Right 방향인지 판단한다")
    void select_right_direction_by_points_position() {
        final Point point1 = new Point(File.A, Rank.TWO);
        final Point point2 = new Point(File.C, Rank.TWO);

        final Direction direction = DirectionUtil.determineDirection(point1, point2);

        assertThat(direction).isEqualTo(Direction.RIGHT);
    }

    @Test
    @DisplayName("두 좌표 위치를 토대로 Up_right 방향인지 판단한다")
    void select_up_right_direction_by_points_position() {
        final Point point1 = new Point(File.A, Rank.TWO);
        final Point point2 = new Point(File.D, Rank.FIVE);

        final Direction direction = DirectionUtil.determineDirection(point1, point2);

        assertThat(direction).isEqualTo(Direction.UP_RIGHT);
    }

    @Test
    @DisplayName("두 좌표 위치를 토대로 Up_left 방향인지 판단한다")
    void select_up_left_direction_by_points_position() {
        final Point point1 = new Point(File.C, Rank.TWO);
        final Point point2 = new Point(File.A, Rank.FOUR);

        final Direction direction = DirectionUtil.determineDirection(point1, point2);

        assertThat(direction).isEqualTo(Direction.UP_LEFT);
    }

    @Test
    @DisplayName("두 좌표 위치를 토대로 Down_right 방향인지 판단한다")
    void select_down_right_direction_by_points_position() {
        final Point point1 = new Point(File.A, Rank.FOUR);
        final Point point2 = new Point(File.C, Rank.TWO);

        final Direction direction = DirectionUtil.determineDirection(point1, point2);

        assertThat(direction).isEqualTo(Direction.DOWN_RIGHT);
    }

    @Test
    @DisplayName("두 좌표 위치를 토대로 Down_left 방향인지 판단한다")
    void select_down_left_direction_by_points_position() {
        final Point point1 = new Point(File.C, Rank.FOUR);
        final Point point2 = new Point(File.A, Rank.TWO);

        final Direction direction = DirectionUtil.determineDirection(point1, point2);

        assertThat(direction).isEqualTo(Direction.DOWN_LEFT);
    }

    @Test
    @DisplayName("위 경우를 모두 만족하지 않으면 예외를 발생한다.")
    void throw_exception_when_not_all_condition_satisfy() {
        final Point point1 = new Point(File.A, Rank.ONE);
        final Point point2 = new Point(File.F, Rank.SEVEN);

        assertThatThrownBy(() -> DirectionUtil.determineDirection(point1, point2))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
