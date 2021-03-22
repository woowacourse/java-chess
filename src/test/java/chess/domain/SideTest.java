package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SideTest {
    @Test
    @DisplayName("턴 교체")
    void changeTurn() {
        assertThat(Side.WHITE.changeTurn()).isEqualTo(Side.BLACK);
        assertThat(Side.BLACK.changeTurn()).isEqualTo(Side.WHITE);
        assertThatThrownBy(() -> Side.NONE.changeTurn()).isInstanceOf(UnsupportedOperationException.class);
    }
}