package chess.domain.square;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SideTest {
    @Test
    @DisplayName("진영 색을 입력 받아 생성한다.")
    void create() {
        // given
        Color color = Color.BLACK;

        // when
        Side side = Side.from(color);

        // expected
        assertThat(side).isNotNull();
    }

    @Test
    @DisplayName("현재 진영의 반대 진영을 찾는다.")
    void findOpponent() {
        // given
        Side whiteSide = Side.from(Color.WHITE);
        Side blackSide = Side.from(Color.BLACK);

        // expected
        assertThat(whiteSide.findOpponent()).isEqualTo(Side.from(Color.BLACK));
        assertThat(blackSide.findOpponent()).isEqualTo(Side.from(Color.WHITE));
    }
}
