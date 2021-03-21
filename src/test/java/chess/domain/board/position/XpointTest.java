package chess.domain.board.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class XpointTest {

    @Test
    @DisplayName("Xpoint 출력을 위한 이름 반환 테스트")
    void testPrintXpoint() {
        assertThat(Xpoint.A.getName()).isEqualTo("a");
        assertThat(Xpoint.B.getName()).isEqualTo("b");
        assertThat(Xpoint.C.getName()).isEqualTo("c");
        assertThat(Xpoint.D.getName()).isEqualTo("d");
        assertThat(Xpoint.E.getName()).isEqualTo("e");
        assertThat(Xpoint.F.getName()).isEqualTo("f");
        assertThat(Xpoint.G.getName()).isEqualTo("g");
        assertThat(Xpoint.H.getName()).isEqualTo("h");
    }

    @Test
    @DisplayName("xpoint left 테스트")
    void leftTest() {
        Xpoint xpoint = Xpoint.D;

        xpoint = xpoint.left();
        assertThat(xpoint).isEqualTo(Xpoint.C);

        xpoint = xpoint.left();
        assertThat(xpoint).isEqualTo(Xpoint.B);

        xpoint = xpoint.left();
        assertThat(xpoint).isEqualTo(Xpoint.A);

        xpoint = xpoint.left();
        assertThat(xpoint).isEqualTo(Xpoint.A);
    }

    @Test
    @DisplayName("xpoint 복수형 left 테스트")
    void integerLeftTest() {
        Xpoint xpoint = Xpoint.D;

        xpoint = xpoint.left(2);
        assertThat(xpoint).isEqualTo(Xpoint.B);

        xpoint = xpoint.left(1);
        assertThat(xpoint).isEqualTo(Xpoint.A);

        xpoint = xpoint.left(3);
        assertThat(xpoint).isEqualTo(Xpoint.A);

        xpoint = xpoint.left(1);
        assertThat(xpoint).isEqualTo(Xpoint.A);
    }

    @Test
    @DisplayName("xpoint right 테스트")
    void rightTest() {
        Xpoint xpoint = Xpoint.E;

        xpoint = xpoint.right();
        assertThat(xpoint).isEqualTo(Xpoint.F);

        xpoint = xpoint.right();
        assertThat(xpoint).isEqualTo(Xpoint.G);

        xpoint = xpoint.right();
        assertThat(xpoint).isEqualTo(Xpoint.H);

        xpoint = xpoint.right();
        assertThat(xpoint).isEqualTo(Xpoint.H);
    }

    @Test
    @DisplayName("xpoint 복수형 right 테스트")
    void integerRightTest() {
        Xpoint xpoint = Xpoint.D;

        xpoint = xpoint.right(2);
        assertThat(xpoint).isEqualTo(Xpoint.F);

        xpoint = xpoint.right(1);
        assertThat(xpoint).isEqualTo(Xpoint.G);

        xpoint = xpoint.right(3);
        assertThat(xpoint).isEqualTo(Xpoint.G);

        xpoint = xpoint.right(1);
        assertThat(xpoint).isEqualTo(Xpoint.H);
    }
}