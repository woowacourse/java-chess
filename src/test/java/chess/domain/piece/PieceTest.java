package chess.domain.piece;

import static chess.domain.position.File.ONE;
import static chess.domain.position.Rank.A;

import chess.domain.position.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PieceTest {
    @DisplayName("Pawn 생성")
    @Test
    public void piece() {
        //given & when & then
        Assertions.assertDoesNotThrow(() -> new Pawn(Team.WHITE, new Position(A, ONE)));
    }
}
