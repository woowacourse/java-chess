package chess.domain.board;

import static chess.domain.fixture.CoordinateFixture.A1;
import static chess.domain.fixture.CoordinateFixture.A2;
import static chess.domain.fixture.CoordinateFixture.A4;
import static chess.domain.fixture.CoordinateFixture.A7;
import static chess.domain.fixture.CoordinateFixture.B1;
import static chess.domain.fixture.CoordinateFixture.B2;
import static chess.domain.fixture.CoordinateFixture.B4;
import static chess.domain.fixture.CoordinateFixture.B6;
import static chess.domain.fixture.CoordinateFixture.C3;
import static chess.domain.fixture.CoordinateFixture.C4;
import static chess.domain.fixture.CoordinateFixture.C5;
import static chess.domain.fixture.CoordinateFixture.D1;
import static chess.domain.fixture.CoordinateFixture.D2;
import static chess.domain.fixture.CoordinateFixture.D3;
import static chess.domain.fixture.CoordinateFixture.D4;
import static chess.domain.fixture.CoordinateFixture.D5;
import static chess.domain.fixture.CoordinateFixture.D6;
import static chess.domain.fixture.CoordinateFixture.D7;
import static chess.domain.fixture.CoordinateFixture.D8;
import static chess.domain.fixture.CoordinateFixture.E3;
import static chess.domain.fixture.CoordinateFixture.E4;
import static chess.domain.fixture.CoordinateFixture.E5;
import static chess.domain.fixture.CoordinateFixture.F2;
import static chess.domain.fixture.CoordinateFixture.F4;
import static chess.domain.fixture.CoordinateFixture.F6;
import static chess.domain.fixture.CoordinateFixture.G1;
import static chess.domain.fixture.CoordinateFixture.G4;
import static chess.domain.fixture.CoordinateFixture.G7;
import static chess.domain.fixture.CoordinateFixture.H4;
import static chess.domain.fixture.CoordinateFixture.H8;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DirectionTest {

    @DisplayName("제자리 경로는 생성할 수 없다.")
    @Test
    void exception() {
        assertThatThrownBy(() -> Direction.of(A1, A1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("-1 0 = left")
    @Test
    void left() {
        Direction direction = Direction.of(B1, A1);

        assertThat(direction).isEqualTo(Direction.LEFT);
    }

    @DisplayName("1 0 = right")
    @Test
    void right() {
        Direction direction = Direction.of(A1, B1);

        assertThat(direction).isEqualTo(Direction.RIGHT);
    }

    @DisplayName("0 1 = up")
    @Test
    void up() {
        Direction direction = Direction.of(A1, A2);

        assertThat(direction).isEqualTo(Direction.UP);
    }

    @DisplayName("0 -1 = down")
    @Test
    void down() {
        Direction direction = Direction.of(A2, A1);

        assertThat(direction).isEqualTo(Direction.DOWN);
    }

    @DisplayName("1 1 = right up")
    @Test
    void rightUp() {
        Direction direction = Direction.of(A1, B2);

        assertThat(direction).isEqualTo(Direction.RIGHT_UP);
    }

    @DisplayName("1 -1 = rightDown")
    @Test
    void rightDown() {
        Direction direction = Direction.of(A2, B1);

        assertThat(direction).isEqualTo(Direction.RIGHT_DOWN);
    }

    @DisplayName("-1 1 = left up")
    @Test
    void leftUp() {
        Direction direction = Direction.of(B1, A2);

        assertThat(direction).isEqualTo(Direction.LEFT_UP);
    }

    @DisplayName("-1 -1 = left down")
    @Test
    void leftDown() {
        Direction direction = Direction.of(B2, A1);

        assertThat(direction).isEqualTo(Direction.LEFT_DOWN);
    }

    @DisplayName("위 방향 경로를 생성한다.")
    @Test
    void upPath() {
        List<Coordinate> path = Direction.UP.createPath(D4);
        List<Coordinate> expected = List.of(D5, D6, D7, D8);

        assertThat(path).containsExactlyElementsOf(expected);
    }

    @DisplayName("아래 방향 경로를 생성한다.")
    @Test
    void downPath() {
        List<Coordinate> path = Direction.DOWN.createPath(D4);
        List<Coordinate> expected = List.of(D3, D2, D1);

        assertThat(path).containsExactlyElementsOf(expected);
    }

    @DisplayName("오른쪽 방향 경로를 생성한다.")
    @Test
    void rightPath() {
        List<Coordinate> path = Direction.RIGHT.createPath(D4);
        List<Coordinate> expected = List.of(E4, F4, G4, H4);

        assertThat(path).containsExactlyElementsOf(expected);
    }

    @DisplayName("왼쪽 방향 경로를 생성한다.")
    @Test
    void leftPath() {
        List<Coordinate> path = Direction.LEFT.createPath(D4);
        List<Coordinate> expected = List.of(C4, B4, A4);

        assertThat(path).containsExactlyElementsOf(expected);
    }

    @DisplayName("우하 대각선 방향 경로를 생성한다.")
    @Test
    void rightDownPath() {
        List<Coordinate> path = Direction.RIGHT_DOWN.createPath(D4);
        List<Coordinate> expected = List.of(E3, F2, G1);

        assertThat(path).containsExactlyElementsOf(expected);
    }

    @DisplayName("우상 대각선 방향 경로를 생성한다.")
    @Test
    void rightUpPath() {
        List<Coordinate> path = Direction.RIGHT_UP.createPath(D4);
        List<Coordinate> expected = List.of(E5, F6, G7, H8);

        assertThat(path).containsExactlyElementsOf(expected);
    }

    @DisplayName("좌하 대각선 방향 경로를 생성한다.")
    @Test
    void leftDownPath() {
        List<Coordinate> path = Direction.LEFT_DOWN.createPath(D4);
        List<Coordinate> expected = List.of(C3, B2, A1);

        assertThat(path).containsExactlyElementsOf(expected);
    }

    @DisplayName("좌상 대각선 방향 경로를 생성한다.")
    @Test
    void leftUpPath() {
        List<Coordinate> path = Direction.LEFT_UP.createPath(D4);
        List<Coordinate> expected = List.of(C5, B6, A7);

        assertThat(path).containsExactlyElementsOf(expected);
    }
}
