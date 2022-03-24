package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {

    private Piece knight;

    @BeforeEach
    void setUp() {
        knight = new Knight(Color.WHITE);
    }

    @Test
    @DisplayName("나이트 기물 생성")
    void createPawn() {
        assertThat(knight).isInstanceOf(Knight.class);
    }
}
