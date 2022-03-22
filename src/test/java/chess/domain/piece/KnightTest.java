package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import chess.domain.Position;

class KnightTest {

    @ParameterizedTest
    @EnumSource(Color.class)
    @DisplayName("나이트 기물 생성")
    void createPawn(Color color) {
        Piece knight = new Knight(color, new Position('a', '1'));
        assertThat(knight).isInstanceOf(Knight.class);
    }
}
