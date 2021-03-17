package chess.piece;

import chess.Point;
import chess.piece.Pawn;
import chess.piece.Pieces;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class PawnTest {
    @DisplayName("Bishop 생성")
    @ParameterizedTest
    @ValueSource(chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'})
    public void create(char column) {
        Pawn blackPawn = new Pawn("P", "BLACK", Point.of(column, 7));
        assertThat(Pieces.findPiece(column, 7)).isEqualTo(blackPawn);
        Pawn whitePawn = new Pawn("p", "WHITE", Point.of(column, 2));
        assertThat(Pieces.findPiece(column, 2)).isEqualTo(whitePawn);
    }
}
