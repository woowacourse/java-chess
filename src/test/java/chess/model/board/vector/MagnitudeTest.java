package chess.model.board.vector;

import chess.model.board.Coordinate;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static chess.model.board.vector.Direction.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class MagnitudeTest {
    @Test
    void 생성자_확인_Coordinates가_null인_경우() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> new Magnitude(null, NORTH));
    }

    @Test
    void 생성자_확인_Coordinates가_빈_경우() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> new Magnitude(Collections.emptyList(), NORTH));
    }

    @Test
    void 생성자_확인_Direction이_빈_경우() {
        List<Coordinate> coordinates = Arrays.asList(
                Coordinate.valueOf(2),
                Coordinate.valueOf(2),
                Coordinate.valueOf(2),
                Coordinate.valueOf(3));

        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> new Magnitude(coordinates, null));
    }

    @Test
    void magnitude값_확인_방향이_북쪽_일_경우() {
        List<Coordinate> coordinates = Arrays.asList(
                Coordinate.valueOf(2),
                Coordinate.valueOf(2),
                Coordinate.valueOf(2),
                Coordinate.valueOf(3));
        Magnitude magnitude = new Magnitude(coordinates, NORTH);

        assertThat(magnitude.getMagnitude()).isEqualTo(1);
    }

    @Test
    void magnitude값_확인_방향이_남쪽_일_경우() {
        List<Coordinate> coordinates = Arrays.asList(
                Coordinate.valueOf(2),
                Coordinate.valueOf(2),
                Coordinate.valueOf(2),
                Coordinate.valueOf(1));
        Magnitude magnitude = new Magnitude(coordinates, SOUTH);

        assertThat(magnitude.getMagnitude()).isEqualTo(1);
    }

    @Test
    void magnitude값_확인_방향이_동쪽_일_경우() {
        List<Coordinate> coordinates = Arrays.asList(
                Coordinate.valueOf(2),
                Coordinate.valueOf(2),
                Coordinate.valueOf(3),
                Coordinate.valueOf(2));
        Magnitude magnitude = new Magnitude(coordinates, EAST);

        assertThat(magnitude.getMagnitude()).isEqualTo(1);
    }

    @Test
    void magnitude값_확인_방향이_서쪽_일_경우() {
        List<Coordinate> coordinates = Arrays.asList(
                Coordinate.valueOf(2),
                Coordinate.valueOf(2),
                Coordinate.valueOf(1),
                Coordinate.valueOf(2));
        Magnitude magnitude = new Magnitude(coordinates, WEST);

        assertThat(magnitude.getMagnitude()).isEqualTo(1);
    }

    @Test
    void magnitude값_확인_방향이_북서쪽_일_경우() {
        List<Coordinate> coordinates = Arrays.asList(
                Coordinate.valueOf(2),
                Coordinate.valueOf(2),
                Coordinate.valueOf(1),
                Coordinate.valueOf(3));
        Magnitude magnitude = new Magnitude(coordinates, NORTHWEST);

        assertThat(magnitude.getMagnitude()).isEqualTo(1);
    }

    @Test
    void magnitude값_확인_방향이_북동쪽_일_경우() {
        List<Coordinate> coordinates = Arrays.asList(
                Coordinate.valueOf(2),
                Coordinate.valueOf(2),
                Coordinate.valueOf(3),
                Coordinate.valueOf(3));
        Magnitude magnitude = new Magnitude(coordinates, NORTHEAST);

        assertThat(magnitude.getMagnitude()).isEqualTo(1);
    }

    @Test
    void magnitude값_확인_방향이_남서쪽_일_경우() {
        List<Coordinate> coordinates = Arrays.asList(
                Coordinate.valueOf(2),
                Coordinate.valueOf(2),
                Coordinate.valueOf(1),
                Coordinate.valueOf(1));
        Magnitude magnitude = new Magnitude(coordinates, SOUTHWEST);

        assertThat(magnitude.getMagnitude()).isEqualTo(1);
    }

    @Test
    void magnitude값_확인_방향이_남동쪽_일_경우() {
        List<Coordinate> coordinates = Arrays.asList(
                Coordinate.valueOf(2),
                Coordinate.valueOf(2),
                Coordinate.valueOf(3),
                Coordinate.valueOf(1));
        Magnitude magnitude = new Magnitude(coordinates, SOUTHEAST);

        assertThat(magnitude.getMagnitude()).isEqualTo(1);
    }

    @Test
    void magnitude값_확인_나이트일_경우() {
        List<Coordinate> coordinates = Arrays.asList(
                Coordinate.valueOf(3),
                Coordinate.valueOf(3),
                Coordinate.valueOf(4),
                Coordinate.valueOf(5));
        Magnitude magnitude = new Magnitude(coordinates, KNIGHT_NORTHEAST);

        assertThat(magnitude.getMagnitude()).isEqualTo(0);
    }
}
