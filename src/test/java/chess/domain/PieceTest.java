package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Piece;
import chess.domain.piece.Shape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class PieceTest {
    @DisplayName("위치와 모양이 같은 기물은 동등하다.")
    @Test
    void equals() {
        Piece piece = Piece.from(1, 'a', Shape.PAWN);

        assertThat(piece).isEqualTo(Piece.from(1, 'a', Shape.PAWN));
    }
}
