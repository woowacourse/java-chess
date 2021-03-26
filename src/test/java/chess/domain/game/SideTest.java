package chess.domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SideTest {

    @Test
    @DisplayName("턴 교체")
    void changeTurn() {
        Assertions.assertThat(Side.WHITE.changeTurn()).isEqualTo(Side.BLACK);
        assertThat(Side.BLACK.changeTurn()).isEqualTo(Side.WHITE);
        assertThatThrownBy(() -> {
            Side side = Side.NONE.changeTurn();
        }).isInstanceOf(UnsupportedOperationException.class);
    }
}