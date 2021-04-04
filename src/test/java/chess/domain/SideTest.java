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

    @Test
    void getTurnByName() {
        assertThat(Side.getTurnByName("WHITE")).isEqualTo(Side.WHITE);
        assertThat(Side.getTurnByName("BLACK")).isEqualTo(Side.BLACK);
        assertThat(Side.getTurnByName("NONE")).isEqualTo(Side.NONE);
    }
}