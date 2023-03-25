package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CampTest {
    @Test
    @DisplayName("흰색 차례에서 검은색 차례로 바뀌는 테스트")
    void getWhiteOppositeTest() {
        Camp white = Camp.WHITE;
        assertThat(white.getOpposite()).isEqualTo(Camp.BLACK);
    }

    @Test
    @DisplayName("검은색 차례에서 흰색 차례로 바뀌는 테스트")
    void getBlackOppositeTest() {
        Camp white = Camp.BLACK;
        assertThat(white.getOpposite()).isEqualTo(Camp.WHITE);
    }
}
