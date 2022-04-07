package chess.domain.board;

import chess.domain.piece.move.StraightDirection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PointTest {

    @Test
    @DisplayName("올바른 값이 들어왔을 떄 정상적으로 Point 를 만드는지 체크한다.")
    void createPointTest() {
        int x = 3;
        int y = 4;

        assertThat(Point.of(x, y)).isNotNull();
    }

    @Test
    @DisplayName("값에 의해 비교할 수 있다.")
    void testEqualsByValue() {
        Point aPoint = Point.of(3, 3);
        Point bPoint = Point.of(3, 3);

        assertThat(aPoint).isEqualTo(bPoint);
    }

    @Test
    @DisplayName("좌표를 문자열로 생성할 수 있다.")
    void createPointByString() {
        Point point = Point.of("a1");

        assertThat(point).isEqualTo(Point.of(1, 1));
    }

    @Test
    @DisplayName("좌표의 가로 거리를 구할 수 있다.")
    void subtractPointsHorizontally() {
        Point aPoint = Point.of("a1");
        Point bPoint = Point.of("b1");

        int subtracted = bPoint.subtractHorizontal(aPoint);

        assertThat(subtracted).isEqualTo(1);
    }

    @Test
    @DisplayName("좌표의 세로 거리를 구할 수 있다.")
    void subtractPointsVertically() {
        Point aPoint = Point.of("a1");
        Point bPoint = Point.of("a2");
        int subtracted = bPoint.subtractVertical(aPoint);

        assertThat(subtracted).isEqualTo(1);
    }

    @Test
    @DisplayName("방향에 의해 다음 좌표를 얻을 수 있다.")
    void goNextByDirection() {
        Point aPoint = Point.of("a1");

        Point next = StraightDirection.NORTHEAST.nextOf(aPoint);

        assertThat(next).isEqualTo(Point.of("b2"));
    }

    @Test
    @DisplayName("Point의 체스판 id를 올바르게 반환한다.")
    void toPointId() {
        Point point = Point.of("a1");

        assertThat(point.convertPointToId()).isEqualTo("a1");
    }
}
