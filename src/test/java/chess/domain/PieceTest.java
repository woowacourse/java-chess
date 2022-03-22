package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PieceTest {

    @Test
    @DisplayName("백기물일 경우 소문자로 표시한다.")
    void white() {
        Piece piece = new Piece(Color.WHITE, Type.KING);
        assertThat(piece.getName()).isEqualTo("k");
    }

    @Test
    @DisplayName("흑기물일 경우 대문자로 표시한다.")
    void black() {
        Piece piece = new Piece(Color.BLACK, Type.KING);
        assertThat(piece.getName()).isEqualTo("K");
    }
}
