package chess.domain.piece;

import chess.domain.piece.ColorType;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("체스말")
public class PieceTest {

    @DisplayName("체스말을 생성한다.")
    @Test
    void createPiece() {
        // when & then
        assertThatCode(() -> new Piece(PieceType.KING, ColorType.WHITE)).doesNotThrowAnyException();
    }
}
