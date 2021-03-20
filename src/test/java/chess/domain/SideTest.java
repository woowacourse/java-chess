package chess.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SideTest {
    @Test
    void changeTurn() {
        assertThat(Side.WHITE.changeTurn()).isEqualTo(Side.BLACK);
        assertThat(Side.BLACK.changeTurn()).isEqualTo(Side.WHITE);
        assertThat(Side.NONE.changeTurn()).isEqualTo(Side.NONE);
    }
}