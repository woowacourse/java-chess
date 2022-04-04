package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.game.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PositionTest {

    @ParameterizedTest
    @ValueSource(strings = {"a1", "b5", "h8"})
    @DisplayName("행과 열이 같으면 동일하다.")
    void cachedPosition(final String position) {
        assertThat(Position.of(position)).isSameAs(Position.of(position));
    }

}
