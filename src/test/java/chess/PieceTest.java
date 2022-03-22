package chess;

import static chess.Type.PAWN;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PieceTest {
    @DisplayName("타입을 받아서 Piece 생성")
    @Test
    public void piece() {
        //given & when & then
        Assertions.assertDoesNotThrow(() -> new Piece(PAWN));
    }

}
