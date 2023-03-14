package chessTest;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chessgame.point.File;
import chessgame.point.Point;
import chessgame.point.Rank;

class PointTest {
    @Test
    @DisplayName("좌표가 같을때 새로운 객체를 생성하지 않는지 확인한다.")
    void Should_Same_When_SamePoint() {
        Point point = Point.of(Rank.A, File.ONE);

        Assertions.assertThat(point)
            .isSameAs(Point.of(Rank.A, File.ONE));
    }
}
