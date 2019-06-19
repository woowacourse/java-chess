package chess.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class DirectionTest {
    @Test
    void 북_반환_테스트() {
        Direction direction = Direction.findDirection(Arrays.asList(
                Coordinate.valueOf(2),
                Coordinate.valueOf(2),
                Coordinate.valueOf(2),
                Coordinate.valueOf(3)));
        assertThat(direction).isEqualTo(Direction.NORTH);
    }

    @Test
    void 남_반환_테스트() {
        Direction direction = Direction.findDirection(Arrays.asList(
                Coordinate.valueOf(2),
                Coordinate.valueOf(2),
                Coordinate.valueOf(2),
                Coordinate.valueOf(1)));
        assertThat(direction).isEqualTo(Direction.SOUTH);
    }

    @Test
    void 서_반환_테스트() {
        Direction direction = Direction.findDirection(Arrays.asList(
                Coordinate.valueOf(2),
                Coordinate.valueOf(2),
                Coordinate.valueOf(1),
                Coordinate.valueOf(2)));
        assertThat(direction).isEqualTo(Direction.WEST);
    }

    @Test
    void 동_반환_테스트() {
        Direction direction = Direction.findDirection(Arrays.asList(
                Coordinate.valueOf(2),
                Coordinate.valueOf(2),
                Coordinate.valueOf(3),
                Coordinate.valueOf(2)));
        assertThat(direction).isEqualTo(Direction.EAST);
    }

    @Test
    void 북서_반환_테스트() {
        Direction direction = Direction.findDirection(Arrays.asList(
                Coordinate.valueOf(2),
                Coordinate.valueOf(2),
                Coordinate.valueOf(1),
                Coordinate.valueOf(3)));
        assertThat(direction).isEqualTo(Direction.NORTHWEST);
    }

    @Test
    void 북동_반환_테스트() {
        Direction direction = Direction.findDirection(Arrays.asList(
                Coordinate.valueOf(2),
                Coordinate.valueOf(2),
                Coordinate.valueOf(3),
                Coordinate.valueOf(3)));
        assertThat(direction).isEqualTo(Direction.NORTHEAST);
    }

    @Test
    void 남서_반환_테스트() {
        Direction direction = Direction.findDirection(Arrays.asList(
                Coordinate.valueOf(2),
                Coordinate.valueOf(2),
                Coordinate.valueOf(1),
                Coordinate.valueOf(1)));
        assertThat(direction).isEqualTo(Direction.SOUTHWEST);
    }

    @Test
    void 남동_반환_테스트() {
        Direction direction = Direction.findDirection(Arrays.asList(
                Coordinate.valueOf(2),
                Coordinate.valueOf(2),
                Coordinate.valueOf(3),
                Coordinate.valueOf(1)));
        assertThat(direction).isEqualTo(Direction.SOUTHEAST);
    }

    @Test
    void 나이트_이동방향_테스트_북서() {
        Direction direction = Direction.findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(2),
                Coordinate.valueOf(5)));
        assertThat(direction).isEqualTo(Direction.KNIGHT_NORTHWEST);
    }

    @Test
    void 나이트_이동방향_테스트_북동() {
        Direction direction = Direction.findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(4),
                Coordinate.valueOf(5)));
        assertThat(direction).isEqualTo(Direction.KNIGHT_NORTHEAST);
    }

    @Test
    void 나이트_이동방향_테스트_동북() {
        Direction direction = Direction.findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(5),
                Coordinate.valueOf(4)));
        assertThat(direction).isEqualTo(Direction.KNIGHT_EASTNORTH);
    }

    @Test
    void 나이트_이동방향_테스트_동남() {
        Direction direction = Direction.findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(5),
                Coordinate.valueOf(2)));
        assertThat(direction).isEqualTo(Direction.KNIGHT_EASTSOUTH);
    }

    @Test
    void 나이트_이동방향_테스트_남서() {
        Direction direction = Direction.findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(2),
                Coordinate.valueOf(1)));
        assertThat(direction).isEqualTo(Direction.KNIGHT_SOUTHWEST);
    }

    @Test
    void 나이트_이동방향_테스트_남동() {
        Direction direction = Direction.findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(4),
                Coordinate.valueOf(1)));
        assertThat(direction).isEqualTo(Direction.KNIGHT_SOUTHEAST);
    }

    @Test
    void 나이트_이동방향_테스트_서북() {
        Direction direction = Direction.findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(1),
                Coordinate.valueOf(4)));
        assertThat(direction).isEqualTo(Direction.KNIGHT_WESTNORTH);
    }

    @Test
    void 나이트_이동방향_테스트_서남() {
        Direction direction = Direction.findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(1),
                Coordinate.valueOf(2)));
        assertThat(direction).isEqualTo(Direction.KNIGHT_WESTSOUTH);
    }
}
