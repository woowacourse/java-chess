package chess.domain.player;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayerTest {

    @ParameterizedTest
    @MethodSource("createPlayer")
    void decidePieceName(Player player, String expected) {
        assertThat(player.decideName("k")).isEqualTo(expected);
    }

    static Stream<Arguments> createPlayer() {
        return Stream.of(
                Arguments.of(Player.BLACK, "K"),
                Arguments.of(Player.WHITE, "k")
        );
    }
}