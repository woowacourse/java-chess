package model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessColorTest {
    @Test
    void 컬러_상수_유무_확인() {
        assertThat(ChessColor.WHITE).isNotNull();
        assertThat(ChessColor.BLACK).isNotNull();
    }
}
