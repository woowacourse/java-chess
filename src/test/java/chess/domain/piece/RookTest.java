package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class RookTest {

    @ParameterizedTest
    @EnumSource(Color.class)
    @DisplayName("룩 기물 생성")
    void createRook(Color color) {
        assertThat(new Rook(color)).isInstanceOf(Rook.class);
    }
}
