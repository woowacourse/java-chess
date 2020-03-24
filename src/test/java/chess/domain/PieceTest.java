package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PieceTest {
    @DisplayName("체스 말 move test")
    @Test
    void moveTest() {
        Piece queen = new Queen(Side.BLACK);
        queen.move(Position.of(Row.FOUR, Column.B));
    }
}
