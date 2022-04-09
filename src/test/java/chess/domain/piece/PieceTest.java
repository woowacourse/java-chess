package chess.domain.piece;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PieceTest {
    @DisplayName("Pawn 생성")
    @Test
    public void piece() {
        //given & when & then
        Assertions.assertDoesNotThrow(() -> new Pawn(Team.WHITE));
    }
}
