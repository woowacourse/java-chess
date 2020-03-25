package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;

public class PieceTest {
    @DisplayName("체스 말 move test")
    @Test
    void moveTest() {
        Piece queen = Piece.of(Type.QUEEN, Side.BLACK);
        queen.move(Position.of(Row.FOUR, Column.B));
    }
}
