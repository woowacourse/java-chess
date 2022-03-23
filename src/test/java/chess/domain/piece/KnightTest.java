package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class KnightTest {

    @ParameterizedTest
    @EnumSource(Color.class)
    @DisplayName("나이트 기물 생성")
    void createPawn(Color color) {
        assertThat(new Knight(color)).isInstanceOf(Knight.class);
    }
}
