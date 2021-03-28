package chess.domain.piece.info;

import chess.domain.piece.info.Color;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ColorTest {
    @Test
    void 같은_색인지_확인한다() {
        boolean result = Color.WHITE.isSame(Color.BLACK);

        assertThat(result).isFalse();
    }

    @Test
    void 색을_바꾼다() {
        Color result = Color.BLACK.reverse();

        assertThat(result).isEqualTo(Color.WHITE);
    }
}
