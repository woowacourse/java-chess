package chess.coordinate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class CoordinateTest {

    @DisplayName("두 좌표 값의 차이를 구한다.")
    @Test
    void calculateVariation() {
        Coordinate source = Coordinate.of(File.A, Rank.ONE);
        Coordinate target = Coordinate.of(File.B, Rank.THREE);
        assertThat(target.calculateVector(source))
                .isEqualTo(new Vector(1, 2));
    }

    @DisplayName("rank가 높은 순으로 정렬 이후 file이 낮은 순으로 정렬")
    @Test
    void compare() {
        //given
        Coordinate a8 = Coordinate.of("a8");
        Coordinate a7 = Coordinate.of("a7");
        Coordinate a6 = Coordinate.of("a6");
        Coordinate b8 = Coordinate.of("b8");
        Coordinate b7 = Coordinate.of("b7");
        Coordinate b6 = Coordinate.of("b6");
        Coordinate c8 = Coordinate.of("c8");

        //when
        List<Coordinate> list = Arrays.asList(c8, a6, a7, b6, b7, b8, a8);
        Collections.sort(list);

        //then
        assertThat(list).containsExactly(a8, b8, c8, a7, b7, a6, b6);
    }
}