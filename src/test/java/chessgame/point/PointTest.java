package chessgame.point;

import chessgame.domain.point.File;
import chessgame.domain.point.Point;
import chessgame.domain.point.Rank;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chessgame.point.PointFixture.*;

class PointTest {

    @Test
    @DisplayName("좌표가 같을때 새로운 객체를 생성하지 않는지 확인한다.")
    void Should_Same_When_SamePoint() {
        Point point = Point.of(File.A, Rank.ONE);

        Assertions.assertThat(point)
                .isSameAs(Point.of(File.A, Rank.ONE));
    }

    @Test
    @DisplayName("좌표 2개 수평인지 확인한다.")
    void isHorizontal() {
        Point source = A1;

        Assertions.assertThat(source.isHorizontal(B1)).isTrue();
        Assertions.assertThat(source.isHorizontal(A2)).isFalse();
    }

    @Test
    @DisplayName("좌표 2개 수직인지 확인한다.")
    void isVertical() {
        Point source = A1;

        Assertions.assertThat(source.isVertical(A2)).isTrue();
        Assertions.assertThat(source.isVertical(B1)).isFalse();
    }

    @Test
    @DisplayName("좌표 2개 대각선인지 확인한다.")
    void isDiagonal() {
        Point source = A1;

        Assertions.assertThat(source.isDiagonal(B2)).isTrue();
    }

    @Test
    @DisplayName("File간 거리를 알 수 있다.")
    void fileDistance() {
        Point source = A1;

        Assertions.assertThat(source.fileDistance(B1)).isEqualTo(-1);
    }

    @Test
    @DisplayName("Rank간 거리를 알 수 있다.")
    void rankDistance() {
        Point source = A1;

        Assertions.assertThat(source.rankDistance(A8)).isEqualTo(-7);
    }

    @Test
    @DisplayName("처음시작 위치를 비교한다.")

    void isInitialPoint() {
        Point source = A1;

        Assertions.assertThat(source.isInitialPoint(Rank.ONE)).isTrue();
    }

    @Test
    @DisplayName("기물이 특정 File 에서 수직인지 확인한다.")

    void isVerticalFile(){
        Point source = A1;

        Assertions.assertThat(source.isVerticalFile(File.A)).isTrue();
    }
}
