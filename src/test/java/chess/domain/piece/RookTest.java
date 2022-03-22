package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import chess.domain.Position;

class RookTest {

    @ParameterizedTest
    @EnumSource(Color.class)
    @DisplayName("룩 기물 생성")
    void createRook(Color color) {
        Piece rook = new Rook(color, new Position('a', '1'));
        assertThat(rook).isInstanceOf(Rook.class);
    }
}
