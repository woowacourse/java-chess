package chess.domain.route;

import static chess.fixture.PositionFixture.A1;
import static chess.fixture.PositionFixture.A2;
import static chess.fixture.PositionFixture.A3;
import static chess.fixture.PositionFixture.A4;
import static chess.fixture.PositionFixture.A5;
import static chess.fixture.PositionFixture.A6;
import static chess.fixture.PositionFixture.A7;
import static chess.fixture.PositionFixture.A8;
import static chess.fixture.PositionFixture.B1;
import static chess.fixture.PositionFixture.B2;
import static chess.fixture.PositionFixture.B7;
import static chess.fixture.PositionFixture.C1;
import static chess.fixture.PositionFixture.C3;
import static chess.fixture.PositionFixture.C6;
import static chess.fixture.PositionFixture.D1;
import static chess.fixture.PositionFixture.D4;
import static chess.fixture.PositionFixture.D5;
import static chess.fixture.PositionFixture.E1;
import static chess.fixture.PositionFixture.E4;
import static chess.fixture.PositionFixture.E5;
import static chess.fixture.PositionFixture.F1;
import static chess.fixture.PositionFixture.F3;
import static chess.fixture.PositionFixture.F6;
import static chess.fixture.PositionFixture.G1;
import static chess.fixture.PositionFixture.G2;
import static chess.fixture.PositionFixture.G7;
import static chess.fixture.PositionFixture.H1;
import static chess.fixture.PositionFixture.H8;
import static org.assertj.core.api.Assertions.assertThat;

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
        Path expected = new Path(List.of(B2, C3, D4, E5, F6, G7));

        Path actual = Path.of(A1, H8);

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
        Path expected = new Path(List.of(G7, F6, E5, D4, C3, B2));

        Path actual = Path.of(H8, A1);

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
        Path expected = new Path(List.of(B7, C6, D5, E4, F3, G2));

        Path actual = Path.of(A8, H1);

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
        Path expected = new Path(List.of(G2, F3, E4, D5, C6, B7));

        Path actual = Path.of(H1, A8);

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
        Path expected = new Path(List.of(B1, C1, D1, E1, F1, G1));

        Path actual = Path.of(A1, H1);

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
        Path expected = new Path(List.of(A2, A3, A4, A5, A6, A7));

        Path actual = Path.of(A1, A8);

        assertThat(actual).isEqualTo(expected);
    }
}
