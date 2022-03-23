package chess.domain.piece;

import static chess.domain.piece.File.*;
import static chess.domain.piece.Rank.*;
import static chess.domain.piece.Type.PAWN;

import chess.domain.Position;
import chess.domain.piece.Piece;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PieceTest {
    @DisplayName("타입을 받아서 Piece 생성")
    @Test
    public void piece() {
        //given & when & then
        Assertions.assertDoesNotThrow(() -> new Piece(PAWN.getSymbol(), new Position(A, ONE)));
    }

}
