package chess.domain.player;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @Test
    void toggle() {
        assertThat(Player.WHITE.toggle()).isEqualTo(Player.BLACK);
        assertThat(Player.BLACK.toggle()).isEqualTo(Player.WHITE);
    }
}