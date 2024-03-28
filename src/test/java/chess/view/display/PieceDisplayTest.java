package chess.view.display;

import static org.assertj.core.api.Assertions.assertThat;

import chess.piece.Color;
import chess.piece.King;
import chess.piece.Piece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceDisplayTest {

    @Test
    @DisplayName("말이 주어졌을 때 올바른 출력 형식으로 변환한다.")
    void notationConversionTest() {
        // given
        Piece piece = new King(Color.WHITE);
        // when
        PieceDisplay display = PieceDisplay.getNotationByPiece(piece);
        // then
        assertThat(display.getNotation()).isEqualTo("k");
    }

    @Test
    @DisplayName("올바르지 않은 말이 주어지면, 없음을 반환한다.")
    void emptyNotationTest() {
        // when
        PieceDisplay display = PieceDisplay.getNotationByPiece(null);
        // then
        assertThat(display.getNotation()).isEqualTo(".");
    }
}
