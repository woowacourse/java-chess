package chess.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PositionTest {
    @Test
    public void 동치성_테스트() {
        Row row = Row.from("1");
        Column column = Column.from("a");
        Position position1 = Position.of(row, column);
        Position position2 = Position.of(row, column);

        assertThat(position1).isEqualTo(position2);
    }

    @Test
    public void 캐싱_테스트() {
        Row row = Row.from("1");
        Column column = Column.from("a");
        Position position1 = Position.of(row, column);
        Position position2 = Position.of(row, column);

        assertTrue(position1 == position2);
    }

    @Test
    public void 캐싱_VALUE_테스트() {
        Row row = Row.from("1");
        Column column = Column.from("a");
        Position position = Position.of(row, column);

        assertTrue(Position.of(row, column) == position);
    }

    @Test
    public void 수평_맞는지_테스트() {
        Position position1 = Position.of("5", "a");
        Position position2 = Position.of("5", "b");

        assertThat(position1.isLevel(position2)).isTrue();
    }

    @Test
    public void 수평_아닌지_테스트() {
        Position position1 = Position.of("3", "a");
        Position position2 = Position.of("5", "a");

        assertThat(position1.isLevel(position2)).isFalse();
    }

    @Test
    public void 수직_맞는지_테스트() {
        Position position1 = Position.of("5", "a");
        Position position2 = Position.of("3", "a");

        assertThat(position1.isPerpendicular(position2)).isTrue();
    }

    @Test
    public void 수직_아닌지_테스트() {
        Position position1 = Position.of("5", "a");
        Position position2 = Position.of("5", "b");

        assertThat(position1.isPerpendicular(position2)).isFalse();
    }

    @Test
    public void 대각선_맞는지_테스트() {
        Position position1 = Position.of("1", "a");
        Position position2 = Position.of("8", "h");

        assertThat(position1.isDiagonal(position2)).isTrue();
    }

    @Test
    public void 대각선_아닌지_테스트() {
        Position position1 = Position.of("6", "e");
        Position position2 = Position.of("4", "d");

        assertThat(position1.isDiagonal(position2)).isFalse();
    }


    @Test
    public void 세로_이동_위쪽_방향() {
        Position origin = Position.of("1", "a");
        Position target = Position.of("8", "a");

        List<Position> actual = new ArrayList<>();
        for (int i = 2; i < 8; i++) {
            actual.add(Position.of(String.valueOf(i), "a"));
        }

        List<Position> expected = origin.findRoutes(target);

        assertTrue(actual.containsAll(expected));
        assertTrue(expected.containsAll(actual));
    }

    @Test
    public void 세로_이동_아래쪽_방향() {
        Position origin = Position.of("8", "a");
        Position target = Position.of("1", "a");

        List<Position> actual = new ArrayList<>();
        for (int i = 2; i < 8; i++) {
            actual.add(Position.of(String.valueOf(i), "a"));
        }

        List<Position> expected = origin.findRoutes(target);

        assertTrue(actual.containsAll(expected));
        assertTrue(expected.containsAll(actual));
    }

    @Test
    public void 가로_이동_오른쪽_방향() {
        String row = "1";
        char originCol = 'a';
        char targetCol = 'e';
        Position origin = Position.of(row, String.valueOf(originCol));
        Position target = Position.of(row, String.valueOf(targetCol));

        List<Position> actual = new ArrayList<>();
        for (int i = originCol+1 ; i < targetCol; i++) {
            Position position = Position.of(row, String.valueOf((char) i));
            actual.add(position);
        }

        List<Position> expected = origin.findRoutes(target);

        assertTrue(actual.containsAll(expected));
        assertTrue(expected.containsAll(actual));
    }

    @Test
    public void 가로_이동_왼쪽_방향() {
        String row = "1";
        char originCol = 'e';
        char targetCol = 'a';
        Position origin = Position.of(row, String.valueOf(originCol));
        Position target = Position.of(row, String.valueOf(targetCol));

        List<Position> actual = new ArrayList<>();
        for (int i = originCol - 1; i > targetCol; i--) {
            Position position = Position.of(row, String.valueOf((char) i));
            actual.add(position);
        }

        List<Position> expected = origin.findRoutes(target);

        assertTrue(actual.containsAll(expected));
        assertTrue(expected.containsAll(actual));
    }

    @Test
    public void 대각선_이동_왼쪽_위_방향() {
        String originRow = "5";
        String targetRow = "2";
        char originCol = 'c';
        char targetCol = 'f';
        Position origin = Position.of(originRow, String.valueOf(originCol));
        Position target = Position.of(targetRow, String.valueOf(targetCol));

        List<Position> actual = new ArrayList<>();
        actual.add(Position.of("4", "d"));
        actual.add(Position.of("3", "e"));

        List<Position> expected = origin.findRoutes(target);

        assertTrue(actual.containsAll(expected));
        assertTrue(expected.containsAll(actual));
    }

    @Test
    public void 대각선_이동_왼쪽_아래_방향() {
        String originRow = "3";
        String targetRow = "6";
        char originCol = 'c';
        char targetCol = 'f';
        Position origin = Position.of(originRow, String.valueOf(originCol));
        Position target = Position.of(targetRow, String.valueOf(targetCol));

        List<Position> actual = new ArrayList<>();
        actual.add(Position.of("4", "d"));
        actual.add(Position.of("5", "e"));

        List<Position> expected = origin.findRoutes(target);

        assertTrue(actual.containsAll(expected));
        assertTrue(expected.containsAll(actual));
    }

    @Test
    public void 대각선_이동_오른쪽_위_방향() {
        String originRow = "4";
        String targetRow = "1";
        char originCol = 'd';
        char targetCol = 'a';
        Position origin = Position.of(originRow, String.valueOf(originCol));
        Position target = Position.of(targetRow, String.valueOf(targetCol));

        List<Position> actual = new ArrayList<>();
        actual.add(Position.of("3", "c"));
        actual.add(Position.of("2", "b"));

        List<Position> expected = origin.findRoutes(target);

        assertTrue(actual.containsAll(expected));
        assertTrue(expected.containsAll(actual));
    }

    @Test
    public void 대각선_이동_오른쪽_아래_방향() {
        String originRow = "4";
        String targetRow = "7";
        char originCol = 'd';
        char targetCol = 'a';
        Position origin = Position.of(originRow, String.valueOf(originCol));
        Position target = Position.of(targetRow, String.valueOf(targetCol));

        List<Position> actual = new ArrayList<>();
        actual.add(Position.of("5", "c"));
        actual.add(Position.of("6", "b"));

        List<Position> expected = origin.findRoutes(target);

        assertTrue(actual.containsAll(expected));
        assertTrue(expected.containsAll(actual));
    }

    @Test
    void 이동_나이트같은_경우() {
        String originRow = "1";
        String targetRow = "3";
        char originCol = 'b';
        char targetCol = 'c';
        Position origin = Position.of(originRow, String.valueOf(originCol));
        Position target = Position.of(targetRow, String.valueOf(targetCol));

        List<Position> expected = origin.findRoutes(target);

        assertTrue(expected.isEmpty());
    }
}
