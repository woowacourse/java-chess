package chessgame.domain.point;

import static chessgame.PointFixture.*;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import chessgame.domain.Team;

class PointsTest {

    @Test
    void fileDistance() {
        Points points = new Points(List.of(A2, B5));
        Assertions.assertThat(points.fileDistance()).isEqualTo(1);
    }

    @Test
    void rankDistance() {
        Points points = new Points(List.of(A2, B5));
        Assertions.assertThat(points.rankDistance()).isEqualTo(3);
    }

    @Test
    void isHorizontal() {
        Points points = new Points(List.of(A2, B2));
        Assertions.assertThat(points.isHorizontal()).isTrue();
    }

    @Test
    void isVertical() {
        Points points = new Points(List.of(A2, A5));
        Assertions.assertThat(points.isVertical()).isTrue();
    }

    @Test
    void isDiagonal() {
        Points points = new Points(List.of(A1, C3));
        Assertions.assertThat(points.isDiagonal()).isTrue();
    }

    @Test
    void isInitialPoint() {
        Points points = new Points(List.of(A2, A4));
        Assertions.assertThat(points.isInitialPoint(Team.WHITE.startRank())).isTrue();
    }

    @Test
    void isSameFileDistance() {
        Points points = new Points(List.of(A2, B2));
        Assertions.assertThat(points.isSameFileDistance(1)).isTrue();
    }

    @Test
    void isSameRankDistance() {
        Points points = new Points(List.of(A2, A4));
        Assertions.assertThat(points.isSameRankDistance(2)).isTrue();
    }

    @Test
    void maxDistance() {
        Points points = new Points(List.of(A8, H4));
        Assertions.assertThat(points.maxDistance()).isEqualTo(7);
    }

    @Test
    void fileMove() {
        Points points = new Points(List.of(A8, H4));
        Assertions.assertThat(points.fileMove(points.maxDistance())).isEqualTo(1);
    }

    @Test
    void rankMove() {
        Points points = new Points(List.of(A8, H4));
        Assertions.assertThat(points.rankMove(points.maxDistance())).isEqualTo(0);
    }
}
