package chess.domain.route;

import static org.assertj.core.api.Assertions.assertThat;

import chess.fixture.PositionFixture;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PathTest {

    /*
    .......t  8
    ......*.  7
    .....*..  6
    ....*...  5
    ...*....  4
    ..*.....  3
    .*......  2
    s.......  1

    abcdefgh
     */
    @DisplayName("오른쪽 위 대각선 경로를 생성한다.")
    @Test
    void makeRightUpDiagonalPath() {
        Path expected = new Path(List.of(PositionFixture.B2, PositionFixture.C3, PositionFixture.D4, PositionFixture.E5, PositionFixture.F6, PositionFixture.G7));

        Path actual = Path.of(PositionFixture.A1, PositionFixture.H8);

        assertThat(actual).isEqualTo(expected);
    }

    /*
    .......s  8
    ......*.  7
    .....*..  6
    ....*...  5
    ...*....  4
    ..*.....  3
    .*......  2
    t.......  1

    abcdefgh
     */
    @DisplayName("왼쪽 아래 대각선 경로를 생성한다.")
    @Test
    void makeLeftDownDiagonalPath() {
        Path expected = new Path(List.of(PositionFixture.G7, PositionFixture.F6, PositionFixture.E5, PositionFixture.D4, PositionFixture.C3, PositionFixture.B2));

        Path actual = Path.of(PositionFixture.H8, PositionFixture.A1);

        assertThat(actual).isEqualTo(expected);
    }

    /*
    s.......  8
    .*......  7
    ..*.....  6
    ...*....  5
    ....*...  4
    .....*..  3
    ......*.  2
    .......t  1

    abcdefgh
     */
    @DisplayName("오른쪽 아래 대각선 경로를 생성한다.")
    @Test
    void makeRightDownDiagonalPath() {
        Path expected = new Path(List.of(PositionFixture.B7, PositionFixture.C6, PositionFixture.D5, PositionFixture.E4, PositionFixture.F3, PositionFixture.G2));

        Path actual = Path.of(PositionFixture.A8, PositionFixture.H1);

        assertThat(actual).isEqualTo(expected);
    }

    /*
    t.......  8
    .*......  7
    ..*.....  6
    ...*....  5
    ....*...  4
    .....*..  3
    ......*.  2
    .......s  1

    abcdefgh
     */
    @DisplayName("왼쪽 위 대각선 경로를 생성한다.")
    @Test
    void makeLeftUpDiagonalPath() {
        Path expected = new Path(List.of(PositionFixture.G2, PositionFixture.F3, PositionFixture.E4, PositionFixture.D5, PositionFixture.C6, PositionFixture.B7));

        Path actual = Path.of(PositionFixture.H1, PositionFixture.A8);

        assertThat(actual).isEqualTo(expected);
    }

    /*
    ........  8
    ........  7
    ........  6
    ........  5
    ........  4
    ........  3
    ........  2
    s******t  1

    abcdefgh
     */
    @DisplayName("수평 경로를 생성한다.")
    @Test
    void makeHorizontalPath() {
        Path expected = new Path(List.of(PositionFixture.B1, PositionFixture.C1, PositionFixture.D1, PositionFixture.E1, PositionFixture.F1, PositionFixture.G1));

        Path actual = Path.of(PositionFixture.A1, PositionFixture.H1);

        assertThat(actual).isEqualTo(expected);
    }

    /*
    t.......  8
    *.......  7
    *.......  6
    *.......  5
    *.......  4
    *.......  3
    *.......  2
    s.......  1

    abcdefgh
     */
    @DisplayName("수직 경로를 생성한다.")
    @Test
    void makeVerticalPath() {
        Path expected = new Path(List.of(PositionFixture.A2, PositionFixture.A3, PositionFixture.A4, PositionFixture.A5, PositionFixture.A6, PositionFixture.A7));

        Path actual = Path.of(PositionFixture.A1, PositionFixture.A8);

        assertThat(actual).isEqualTo(expected);
    }
}
