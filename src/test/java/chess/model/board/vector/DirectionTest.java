package chess.model.board.vector;

import chess.model.board.Coordinate;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static chess.model.board.vector.Direction.*;
import static org.assertj.core.api.Assertions.assertThat;

public class DirectionTest {
    @Test
    void 북_이동방향_확인() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(2),
                Coordinate.valueOf(2),
                Coordinate.valueOf(2),
                Coordinate.valueOf(3)));
        assertThat(direction).isEqualTo(NORTH);
    }

    @Test
    void 남_이동방향_확인() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(2),
                Coordinate.valueOf(2),
                Coordinate.valueOf(2),
                Coordinate.valueOf(1)));
        assertThat(direction).isEqualTo(SOUTH);
    }

    @Test
    void 서_이동방향_확인() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(2),
                Coordinate.valueOf(2),
                Coordinate.valueOf(1),
                Coordinate.valueOf(2)));
        assertThat(direction).isEqualTo(WEST);
    }

    @Test
    void 동_이동방향_확인() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(2),
                Coordinate.valueOf(2),
                Coordinate.valueOf(3),
                Coordinate.valueOf(2)));
        assertThat(direction).isEqualTo(EAST);
    }

    @Test
    void 북서_이동방향_확인() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(2),
                Coordinate.valueOf(2),
                Coordinate.valueOf(1),
                Coordinate.valueOf(3)));
        assertThat(direction).isEqualTo(NORTHWEST);
    }

    @Test
    void 북동_이동방향_확인() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(2),
                Coordinate.valueOf(2),
                Coordinate.valueOf(3),
                Coordinate.valueOf(3)));
        assertThat(direction).isEqualTo(NORTHEAST);
    }

    @Test
    void 남서_이동방향_확인() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(2),
                Coordinate.valueOf(2),
                Coordinate.valueOf(1),
                Coordinate.valueOf(1)));
        assertThat(direction).isEqualTo(SOUTHWEST);
    }

    @Test
    void 남동_이동방향_확인() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(2),
                Coordinate.valueOf(2),
                Coordinate.valueOf(3),
                Coordinate.valueOf(1)));
        assertThat(direction).isEqualTo(SOUTHEAST);
    }

    @Test
    void 나이트_이동방향_확인_북서() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(2),
                Coordinate.valueOf(5)));
        assertThat(direction).isEqualTo(KNIGHT_NORTHWEST);
    }

    @Test
    void 나이트_이동방향_확인_북동() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(4),
                Coordinate.valueOf(5)));
        assertThat(direction).isEqualTo(KNIGHT_NORTHEAST);
    }

    @Test
    void 나이트_이동방향_확인_동북() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(5),
                Coordinate.valueOf(4)));
        assertThat(direction).isEqualTo(KNIGHT_EASTNORTH);
    }

    @Test
    void 나이트_이동방향_확인_동남() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(5),
                Coordinate.valueOf(2)));
        assertThat(direction).isEqualTo(KNIGHT_EASTSOUTH);
    }

    @Test
    void 나이트_이동방향_확인_남서() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(2),
                Coordinate.valueOf(1)));
        assertThat(direction).isEqualTo(KNIGHT_SOUTHWEST);
    }

    @Test
    void 나이트_이동방향_확인_남동() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(4),
                Coordinate.valueOf(1)));
        assertThat(direction).isEqualTo(KNIGHT_SOUTHEAST);
    }

    @Test
    void 나이트_이동방향_확인_서북() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(1),
                Coordinate.valueOf(4)));
        assertThat(direction).isEqualTo(KNIGHT_WESTNORTH);
    }

    @Test
    void 나이트_이동방향_확인_서남() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(1),
                Coordinate.valueOf(2)));
        assertThat(direction).isEqualTo(KNIGHT_WESTSOUTH);
    }

    @Test
    void 잘못된_방향_확인() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(4),
                Coordinate.valueOf(8)));
        assertThat(direction).isEqualTo(FAULT_DIRECTION);
    }

    @Test
    void 대각선_방향인지_확인_북동() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(4),
                Coordinate.valueOf(4)));
        assertThat(isDiagonal(direction)).isTrue();
    }

    @Test
    void 대각선_방향인지_확인_북서() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(2),
                Coordinate.valueOf(4)));
        assertThat(isDiagonal(direction)).isTrue();
    }

    @Test
    void 대각선_방향인지_확인_남동() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(4),
                Coordinate.valueOf(2)));
        assertThat(isDiagonal(direction)).isTrue();
    }

    @Test
    void 대각선_방향인지_확인_남서() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(2),
                Coordinate.valueOf(2)));
        assertThat(isDiagonal(direction)).isTrue();
    }

    @Test
    void 대각선_방향인지_오류확인_서() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(2),
                Coordinate.valueOf(3)));
        assertThat(isDiagonal(direction)).isFalse();
    }

    @Test
    void 대각선_방향인지_오류확인_동() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(4),
                Coordinate.valueOf(3)));
        assertThat(isDiagonal(direction)).isFalse();
    }

    @Test
    void 대각선_방향인지_오류확인_북() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(4)));
        assertThat(isDiagonal(direction)).isFalse();
    }

    @Test
    void 대각선_방향인지_오류확인_남() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(2)));
        assertThat(isDiagonal(direction)).isFalse();
    }

    @Test
    void 대각선_방향인지_오류확인_없는_방향() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(4),
                Coordinate.valueOf(8)));
        assertThat(isDiagonal(direction)).isFalse();
    }

    @Test
    void 대각선_방향인지_오류확인_나이트_북서() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(2),
                Coordinate.valueOf(5)));
        assertThat(isDiagonal(direction)).isFalse();
    }

    @Test
    void 대각선_방향인지_오류확인_나이트_북동() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(4),
                Coordinate.valueOf(5)));
        assertThat(isDiagonal(direction)).isFalse();
    }

    @Test
    void 대각선_방향인지_오류확인_나이트_동북() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(5),
                Coordinate.valueOf(4)));
        assertThat(isDiagonal(direction)).isFalse();
    }

    @Test
    void 대각선_방향인지_오류확인_나이트_동남() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(5),
                Coordinate.valueOf(2)));
        assertThat(isDiagonal(direction)).isFalse();
    }

    @Test
    void 대각선_방향인지_오류확인_나이트_남서() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(2),
                Coordinate.valueOf(1)));
        assertThat(isDiagonal(direction)).isFalse();
    }

    @Test
    void 대각선_방향인지_오류확인_나이트_남동() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(4),
                Coordinate.valueOf(1)));
        assertThat(isDiagonal(direction)).isFalse();
    }

    @Test
    void 대각선_방향인지_오류확인_나이트_서북() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(1),
                Coordinate.valueOf(4)));
        assertThat(isDiagonal(direction)).isFalse();
    }

    @Test
    void 대각선_방향인지_오류확인_나이트_서남() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(1),
                Coordinate.valueOf(2)));
        assertThat(isDiagonal(direction)).isFalse();
    }

    @Test
    void 수직_방향인지_확인_북() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(4)));
        assertThat(isVertical(direction)).isTrue();
    }

    @Test
    void 수직_방향인지_확인_남() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(2)));
        assertThat(isVertical(direction)).isTrue();
    }

    @Test
    void 수직_방향인지_오류확인_서() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(2),
                Coordinate.valueOf(3)));
        assertThat(isVertical(direction)).isFalse();
    }

    @Test
    void 수직_방향인지_오류확인_동() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(4),
                Coordinate.valueOf(3)));
        assertThat(isVertical(direction)).isFalse();
    }

    @Test
    void 수직_방향인지_오류확인_남동() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(4),
                Coordinate.valueOf(2)));
        assertThat(isVertical(direction)).isFalse();
    }

    @Test
    void 수직_방향인지_오류확인_남서() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(2),
                Coordinate.valueOf(2)));
        assertThat(isVertical(direction)).isFalse();
    }

    @Test
    void 수직_방향인지_오류확인_북동() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(4),
                Coordinate.valueOf(4)));
        assertThat(isVertical(direction)).isFalse();
    }

    @Test
    void 수직_방향인지_오류확인_북서() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(2),
                Coordinate.valueOf(4)));
        assertThat(isVertical(direction)).isFalse();
    }

    @Test
    void 수직_방향인지_오류확인_없는_방향() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(4),
                Coordinate.valueOf(8)));
        assertThat(isVertical(direction)).isFalse();
    }

    @Test
    void 수직_방향인지_오류확인_나이트_북서() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(2),
                Coordinate.valueOf(5)));
        assertThat(isVertical(direction)).isFalse();
    }

    @Test
    void 수직_방향인지_오류확인_나이트_북동() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(4),
                Coordinate.valueOf(5)));
        assertThat(isVertical(direction)).isFalse();
    }

    @Test
    void 수직_방향인지_오류확인_나이트_동북() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(5),
                Coordinate.valueOf(4)));
        assertThat(isVertical(direction)).isFalse();
    }

    @Test
    void 수직_방향인지_오류확인_나이트_동남() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(5),
                Coordinate.valueOf(2)));
        assertThat(isVertical(direction)).isFalse();
    }

    @Test
    void 수직_방향인지_오류확인_나이트_남서() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(2),
                Coordinate.valueOf(1)));
        assertThat(isVertical(direction)).isFalse();
    }

    @Test
    void 수직_방향인지_오류확인_나이트_남동() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(4),
                Coordinate.valueOf(1)));
        assertThat(isVertical(direction)).isFalse();
    }

    @Test
    void 수직_방향인지_오류확인_나이트_서북() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(1),
                Coordinate.valueOf(4)));
        assertThat(isVertical(direction)).isFalse();
    }

    @Test
    void 수직_방향인지_오류확인_나이트_서남() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(1),
                Coordinate.valueOf(2)));
        assertThat(isVertical(direction)).isFalse();
    }

    @Test
    void 수평_방향인지_확인_서() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(2),
                Coordinate.valueOf(3)));
        assertThat(isHorizontal(direction)).isTrue();
    }

    @Test
    void 수평_방향인지_확인_동() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(4),
                Coordinate.valueOf(3)));
        assertThat(isHorizontal(direction)).isTrue();
    }

    @Test
    void 수평_방향인지_오류확인_북() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(4)));
        assertThat(isHorizontal(direction)).isFalse();
    }

    @Test
    void 수평_방향인지_오류확인_남() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(4)));
        assertThat(isHorizontal(direction)).isFalse();
    }

    @Test
    void 수평_방향인지_오류확인_남동() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(4),
                Coordinate.valueOf(2)));
        assertThat(isHorizontal(direction)).isFalse();
    }

    @Test
    void 수평_방향인지_오류확인_남서() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(2),
                Coordinate.valueOf(2)));
        assertThat(isHorizontal(direction)).isFalse();
    }

    @Test
    void 수평_방향인지_오류확인_북동() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(4),
                Coordinate.valueOf(4)));
        assertThat(isHorizontal(direction)).isFalse();
    }

    @Test
    void 수평_방향인지_오류확인_북서() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(2),
                Coordinate.valueOf(4)));
        assertThat(isHorizontal(direction)).isFalse();
    }

    @Test
    void 수평_방향인지_오류확인_없는_방향() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(4),
                Coordinate.valueOf(8)));
        assertThat(isHorizontal(direction)).isFalse();
    }

    @Test
    void 수평_방향인지_오류확인_나이트_북서() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(2),
                Coordinate.valueOf(5)));
        assertThat(isHorizontal(direction)).isFalse();
    }

    @Test
    void 수평_방향인지_오류확인_나이트_북동() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(4),
                Coordinate.valueOf(5)));
        assertThat(isHorizontal(direction)).isFalse();
    }

    @Test
    void 수평_방향인지_오류확인_나이트_동북() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(5),
                Coordinate.valueOf(4)));
        assertThat(isHorizontal(direction)).isFalse();
    }

    @Test
    void 수평_방향인지_오류확인_나이트_동남() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(5),
                Coordinate.valueOf(2)));
        assertThat(isHorizontal(direction)).isFalse();
    }

    @Test
    void 수평_방향인지_오류확인_나이트_남서() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(2),
                Coordinate.valueOf(1)));
        assertThat(isHorizontal(direction)).isFalse();
    }

    @Test
    void 수평_방향인지_오류확인_나이트_남동() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(4),
                Coordinate.valueOf(1)));
        assertThat(isHorizontal(direction)).isFalse();
    }

    @Test
    void 수평_방향인지_오류확인_나이트_서북() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(1),
                Coordinate.valueOf(4)));
        assertThat(isHorizontal(direction)).isFalse();
    }

    @Test
    void 수평_방향인지_오류확인_나이트_서남() {
        Direction direction = findDirection(Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(1),
                Coordinate.valueOf(2)));
        assertThat(isHorizontal(direction)).isFalse();
    }
}
