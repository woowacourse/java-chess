package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PawnTest {

    @Test
    @DisplayName("Blank 가 아닌지 확인")
    void isBlank() {
        Pawn pawn = new Pawn(Color.BLACK);
        assertThat(pawn.isBlank()).isFalse();
    }
}
