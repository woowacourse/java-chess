package chess.domain.board;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.piece.move.straight.StraightDirection;

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
    @DisplayName("문자열의 길이가 잘못되었을 경우 예외를 던진다.")
    public void throwsExceptionWithInvalidStringSize() {
        // given & when
        String input = "a12";
        // then
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> Point.of("a12"));
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

        Point next = aPoint.next(StraightDirection.NORTHEAST);

        assertThat(next).isEqualTo(Point.of("b2"));
    }
}
