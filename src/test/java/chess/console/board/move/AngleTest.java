package chess.console.board.move;

import chess.domain.board.Position;
import chess.domain.board.move.Angle;
import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AngleTest {

    @Test
    @DisplayName("richard")
    void d() {
        final boolean b = Angle.validate(new Pawn(Color.WHITE), Position.from("c4"), Position.from("d3"));
    }
}
