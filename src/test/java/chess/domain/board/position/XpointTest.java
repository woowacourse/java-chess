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
}