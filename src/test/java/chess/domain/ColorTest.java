package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Color;
import org.junit.jupiter.api.Test;

public class ColorTest {
    @Test
    void 흰색_규칙에_따른_출력_형태로_변환() {
        Color color = Color.WHITE;
        assertThat(color.formatName("K")).isEqualTo("k");
    }

    @Test
    void 검은색_규칙에_따른_출력_형태로_변환() {
        Color color = Color.BLACK;
        assertThat(color.formatName("q")).isEqualTo("Q");
    }
}
