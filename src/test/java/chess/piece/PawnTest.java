package chess.piece;

import chess.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class PawnTest {
    @DisplayName("Bishop 생성")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7})
    public void create(int column) {
        Pawn blackPawn = new Pawn("P", "BLACK", Point.valueOf(1, column));
        assertThat(Pieces.findPiece(1, column)).isEqualTo(blackPawn);
        Pawn whitePawn = new Pawn("p", "WHITE", Point.valueOf(6, column));
        assertThat(Pieces.findPiece(6, column)).isEqualTo(whitePawn);
    }
}
