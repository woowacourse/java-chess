package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import chess.domain.board.Coordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DirectionTest {

    @DisplayName("위 방향 경로를 생성한다.")
    @Test
    void upPath() {
        Coordinate start = new Coordinate(4, 'd');
        List<Coordinate> path = Direction.UP.createPath(start);
        List<Coordinate> expected = List.of(
                new Coordinate(5, 'd'),
                new Coordinate(6, 'd'),
                new Coordinate(7, 'd'),
                new Coordinate(8, 'd')
        );

        assertThat(path).containsExactlyElementsOf(expected);
    }

    @DisplayName("아래 방향 경로를 생성한다.")
    @Test
    void downPath() {
        Coordinate start = new Coordinate(4, 'd');
        List<Coordinate> path = Direction.DOWN.createPath(start);
        List<Coordinate> expected = List.of(
                new Coordinate(3, 'd'),
                new Coordinate(2, 'd'),
                new Coordinate(1, 'd')
        );

        assertThat(path).containsExactlyElementsOf(expected);
    }

    @DisplayName("오른쪽 방향 경로를 생성한다.")
    @Test
    void rightPath() {
        Coordinate start = new Coordinate(4, 'd');
        List<Coordinate> path = Direction.RIGHT.createPath(start);
        List<Coordinate> expected = List.of(
                new Coordinate(4, 'e'),
                new Coordinate(4, 'f'),
                new Coordinate(4, 'g'),
                new Coordinate(4, 'h')
        );

        assertThat(path).containsExactlyElementsOf(expected);
    }

    @DisplayName("왼쪽 방향 경로를 생성한다.")
    @Test
    void leftPath() {
        Coordinate start = new Coordinate(4, 'd');
        List<Coordinate> path = Direction.LEFT.createPath(start);
        List<Coordinate> expected = List.of(
                new Coordinate(4, 'c'),
                new Coordinate(4, 'b'),
                new Coordinate(4, 'a')
        );

        assertThat(path).containsExactlyElementsOf(expected);
    }

    @DisplayName("우하 대각선 방향 경로를 생성한다.")
    @Test
    void rightDownPath() {
        Coordinate start = new Coordinate(4, 'd');
        List<Coordinate> path = Direction.RIGHT_DOWN.createPath(start);
        List<Coordinate> expected = List.of(
                new Coordinate(3, 'e'),
                new Coordinate(2, 'f'),
                new Coordinate(1, 'g')
        );

        assertThat(path).containsExactlyElementsOf(expected);
    }

    @DisplayName("우상 대각선 방향 경로를 생성한다.")
    @Test
    void rightUpPath() {
        Coordinate start = new Coordinate(4, 'd');
        List<Coordinate> path = Direction.RIGHT_UP.createPath(start);
        List<Coordinate> expected = List.of(
                new Coordinate(5, 'e'),
                new Coordinate(6, 'f'),
                new Coordinate(7, 'g'),
                new Coordinate(8, 'h')
        );

        assertThat(path).containsExactlyElementsOf(expected);
    }

    @DisplayName("좌하 대각선 방향 경로를 생성한다.")
    @Test
    void leftDownPath() {
        Coordinate start = new Coordinate(4, 'd');
        List<Coordinate> path = Direction.LEFT_DOWN.createPath(start);
        List<Coordinate> expected = List.of(
                new Coordinate(3, 'c'),
                new Coordinate(2, 'b'),
                new Coordinate(1, 'a')
        );

        assertThat(path).containsExactlyElementsOf(expected);
    }

    @DisplayName("좌상 대각선 방향 경로를 생성한다.")
    @Test
    void leftUpPath() {
        Coordinate start = new Coordinate(4, 'd');
        List<Coordinate> path = Direction.LEFT_UP.createPath(start);
        List<Coordinate> expected = List.of(
                new Coordinate(5, 'c'),
                new Coordinate(6, 'b'),
                new Coordinate(7, 'a')
        );

        assertThat(path).containsExactlyElementsOf(expected);
    }
}
