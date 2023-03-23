package chessgame.domain.point;

import static chessgame.domain.point.PointFixture.*;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PointTest {

    @Test
    @DisplayName("좌표가 같을때 새로운 객체를 생성하지 않는지 확인한다.")
    void Should_Same_When_SamePoint() {
        Point point = Point.of(File.A, Rank.ONE);

        Assertions.assertThat(point)
            .isSameAs(Point.of(File.A, Rank.ONE));
    }

    @Test
    @DisplayName("좌표 2개 수평인지 아닌지 확인한다.")
    void isHorizontal() {
        Points horizentalPoints = new Points(List.of(A1, B1));
        Points notHorizentalPoints = new Points(List.of(A1, A2));

        Assertions.assertThat(horizentalPoints.isHorizontal()).isTrue();
        Assertions.assertThat(notHorizentalPoints.isHorizontal()).isFalse();
    }

    @Test
    @DisplayName("좌표 2개 수직인지 아닌지 확인한다.")
    void isVertical() {
        Points verticalPoints = new Points(List.of(A1, A2));
        Points notVerticalPoints = new Points(List.of(A1, B1));

        Assertions.assertThat(verticalPoints.isVertical()).isTrue();
        Assertions.assertThat(notVerticalPoints.isVertical()).isFalse();
    }

    @Test
    @DisplayName("좌표 2개 대각선인지 확인한다.")
    void isDiagonal() {
        Points diagonalPoints = new Points(List.of(A1, B2));

        Assertions.assertThat(diagonalPoints.isDiagonal()).isTrue();
    }

    @Test
    @DisplayName("File간 거리를 알 수 있다.")
    void fileDistance() {
        Assertions.assertThat(A1.fileDistance(B1)).isEqualTo(-1);
    }

    @Test
    @DisplayName("Rank간 거리를 알 수 있다.")
    void rankDistance() {
        Assertions.assertThat(A1.rankDistance(A8)).isEqualTo(-7);
    }

    @Test
    void isInitialPoint() {
        Assertions.assertThat(A1.isInitialPoint(Rank.ONE)).isTrue();
    }
}
