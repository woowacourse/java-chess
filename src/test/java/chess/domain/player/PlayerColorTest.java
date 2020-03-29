package chess.domain.player;

import chess.domain.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerColorTest {

    static Stream<Arguments> createPlayer() {
        return Stream.of(
                Arguments.of(PlayerColor.BLACK, "K"),
                Arguments.of(PlayerColor.WHITE, "k")
        );
    }

    static Stream<Arguments> createPosition() {
        return Stream.of(
                Arguments.of(PlayerColor.BLACK, Position.from("b1"), Position.from("b8")),
                Arguments.of(PlayerColor.WHITE, Position.from("b1"), Position.from("b1"))
        );
    }

    @ParameterizedTest
    @DisplayName("플레이어별 기물 이름")
    @MethodSource("createPlayer")
    void decidePieceName(PlayerColor playerColor, String expected) {
        assertThat(playerColor.decideName("k")).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("플레이어별 기물 초기 위치")
    @MethodSource("createPosition")
    void reviseInitialPosition(PlayerColor playerColor, Position initial, Position expected) {
        assertThat(playerColor.reviseInitialPosition(initial)).isEqualTo(expected);
    }
}