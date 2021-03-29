package domain.position;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

    @DisplayName("배열 좌표계를 사용하여 포지션을 생성한다.")
    @Test
    void createFromArrayCoordinateTest() {
        assertThat(new Position(3, 3)).isEqualTo(new Position(3, 3));
        assertThat(new Position(0, 0)).isEqualTo(new Position(0, 0));
        assertThat(new Position(1, 5)).isEqualTo(new Position(1, 5));
        assertThat(new Position(5, 1)).isEqualTo(new Position(5, 1));
        assertThat(new Position(7, 7)).isEqualTo(new Position(7, 7));
    }

    @DisplayName("체스 좌표계를 사용하여 포지션을 생성한다.")
    @Test
    void createFromChessCoordinateTest() {
        assertThat(new Position("d5")).isEqualTo(new Position(3, 3));
        assertThat(new Position("a8")).isEqualTo(new Position(0, 0));
        assertThat(new Position("f7")).isEqualTo(new Position(1, 5));
        assertThat(new Position("b3")).isEqualTo(new Position(5, 1));
        assertThat(new Position("h1")).isEqualTo(new Position(7, 7));
    }

    @DisplayName("포지션이 체스판 범위에 들지 않는다.")
    @Test
    void isNotChessBoardPositionTest() {
        assertThatThrownBy(() -> new Position("k1"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[Error] 유효하지 않은 체스 좌표 입니다.");
    }

    @DisplayName("포지션이 체스판 범위에 든다.")
    @Test
    void isChessBoardPositionTest() {
        Position position = new Position("h1");
        assertThat(position.isChessBoardPosition()).isTrue();
    }


    @DisplayName("두 포지션은 수평 또는 수직이 아니다.")
    @Test
    void isNotLinearPositionTest() {
        Position source = new Position("d5");
        Position target = new Position("e6");

        assertThat(source.isNotLinearPosition(target)).isTrue();
    }

    @DisplayName("두 포지션은 수평 또는 수직이다.")
    @Test
    void isLinearPositionTest() {
        Position source = new Position("d5");
        Position target = new Position("h5");

        assertThat(source.isNotLinearPosition(target)).isFalse();
    }

    @DisplayName("두 포지션은 대각선 상에 있지 않다.")
    @Test
    void isNotDiagonalPosition() {
        Position source = new Position("d5");
        Position target = new Position("e5");

        assertThat(source.isNotDiagonalPosition(target)).isTrue();
    }

    @DisplayName("두 포지션은 대각선 상에 있다.")
    @Test
    void isDiagonalPosition() {
        Position source = new Position("d5");
        Position target = new Position("e6");

        assertThat(source.isNotDiagonalPosition(target)).isFalse();
    }

    @DisplayName("특정 방향으로 이동한 포지션을 생성한다.")
    @Test
    void sumTest() {
        Position source = new Position(1, 3);
        Position result = source.sum(Direction.EAST);

        assertThat(result).isEqualTo(new Position(1, 4));
    }

    @DisplayName("두 포지션의 차이를 갖는 포지션을 생성한다.")
    @Test
    void diffTest() {
        Position source = new Position(3, 3);
        Position target = new Position(5, 7);
        Position diff = source.difference(target);

        assertThat(diff).isEqualTo(new Position(2, 4));
    }

}