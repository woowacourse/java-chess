package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PawnTest {

    @DisplayName("폰인지를 확인")
    @Test
    public void isPawn() {
        //given
        Pawn pawn = new Pawn(Team.WHITE, "p");

        //when & then
        assertThat(pawn.isPawn()).isTrue();
    }
}
