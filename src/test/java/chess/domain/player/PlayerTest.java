package chess.domain.player;

import static org.assertj.core.api.Assertions.*;

import java.security.spec.ECField;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.board.Position;

class PlayerTest {

    @ParameterizedTest
    @DisplayName("플레이어별 기물 이름")
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

    @ParameterizedTest
    @DisplayName("플레이어별 기물 초기 위치")
    @MethodSource("createPosition")
    void reviseInitialPosition(Player player, Position initial, Position expected) {
        assertThat(player.reviseInitialPosition(initial)).isEqualTo(expected);
    }

    static Stream<Arguments> createPosition() {
        return Stream.of(
                Arguments.of(Player.BLACK, Position.from("b1"), Position.from("b8")),
                Arguments.of(Player.WHITE, Position.from("b1"), Position.from("b1"))
        );
    }
}