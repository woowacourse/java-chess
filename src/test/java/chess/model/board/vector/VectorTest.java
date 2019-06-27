package chess.model.board.vector;

import chess.model.board.Coordinate;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static chess.model.board.vector.Direction.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class VectorTest {
    @Test
    void 생성자_확인_coordinates가_null일_경우() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> new Vector(null));
    }

    @Test
    void 생성자_확인_coordinates가_비어있을_경우() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> new Vector(Collections.emptyList()));
    }

    @Test
    void 방향과_크기가_올바른지_확인_북쪽일_경우() {
        List<Coordinate> coordinates = Arrays.asList(
                Coordinate.valueOf(2),
                Coordinate.valueOf(2),
                Coordinate.valueOf(2),
                Coordinate.valueOf(3));
        Vector vector = new Vector(coordinates);

        assertThat(vector.isEqualToDirection(NORTH)).isTrue();
        assertThat(vector.isEqualToMagnitude(1)).isTrue();
    }

    @Test
    void 방향과_크기가_올바른지_확인_서쪽일_경우() {
        List<Coordinate> coordinates = Arrays.asList(
                Coordinate.valueOf(2),
                Coordinate.valueOf(2),
                Coordinate.valueOf(1),
                Coordinate.valueOf(2));
        Vector vector = new Vector(coordinates);

        assertThat(vector.isEqualToDirection(WEST)).isTrue();
        assertThat(vector.isEqualToMagnitude(1)).isTrue();
    }

    @Test
    void 방향과_크기가_올바른지_확인_북동쪽일_경우() {
        List<Coordinate> coordinates = Arrays.asList(
                Coordinate.valueOf(2),
                Coordinate.valueOf(2),
                Coordinate.valueOf(3),
                Coordinate.valueOf(3));
        Vector vector = new Vector(coordinates);

        assertThat(vector.isEqualToDirection(NORTHEAST)).isTrue();
        assertThat(vector.isEqualToMagnitude(1)).isTrue();
    }

    @Test
    void 방향과_크기가_올바른지_확인_나이트방향_북서쪽일_경우() {
        List<Coordinate> coordinates = Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(2),
                Coordinate.valueOf(5));
        Vector vector = new Vector(coordinates);

        assertThat(vector.isEqualToDirection(KNIGHT_NORTHWEST)).isTrue();
        assertThat(vector.isEqualToMagnitude(0)).isTrue();
    }
}
