package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EmptyPieceTest {

    @Test
    @DisplayName("Empty Piece일 경우 getName에서 .을 반환한다.")
    void getName() {
        Piece piece = new EmptyPiece();
        assertThat(piece.getName()).isEqualTo(".");
    }
}