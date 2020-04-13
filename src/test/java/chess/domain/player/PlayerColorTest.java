package chess.domain.player;

import static org.assertj.core.api.Assertions.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.board.Position;

class PlayerColorTest {

    @ParameterizedTest
    @DisplayName("플레이어별 기물 이름")
    @MethodSource("createPlayer")
    void decidePieceName(PlayerColor playerColor, String expected) {
        assertThat(playerColor.decideName("k")).isEqualTo(expected);
    }

    static Stream<Arguments> createPlayer() {
        return Stream.of(
                Arguments.of(PlayerColor.BLACK, "K"),
                Arguments.of(PlayerColor.WHITE, "k")
        );
    }

    @ParameterizedTest
    @DisplayName("플레이어별 기물 초기 위치")
    @MethodSource("createPosition")
    void reviseInitialPosition(PlayerColor playerColor, List<Position> initial, List<Position> expected) {
        assertThat(playerColor.reviseInitialPositions(initial)).isEqualTo(expected);
    }

    static Stream<Arguments> createPosition() {
        return Stream.of(
                Arguments.of(PlayerColor.BLACK, Collections.singletonList(Position.from("b1")),
                        Collections.singletonList(Position.from("b8"))),
                Arguments.of(PlayerColor.WHITE, Collections.singletonList(Position.from("b1")),
                        Collections.singletonList(Position.from("b1")))
        );
    }
}