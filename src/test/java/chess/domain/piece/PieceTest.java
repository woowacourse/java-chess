package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {

    @Test
    @DisplayName("같은 색깔의 Piece가 들어오면 true를 반환한다.")
    void hasSameColorWith() {
        // given
        Piece piece = new King(Color.WHITE);
        Piece otherPiece = new King(Color.WHITE);
        // when
        boolean result = piece.hasSameColorWith(otherPiece);
        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("다른 색깔의 Piece가 들어오면 false를 반환한다.")
    void hasDifferentColorWith() {
        // given
        Piece piece = new King(Color.WHITE);
        Piece otherPiece = new King(Color.BLACK);
        // when
        boolean result = piece.hasSameColorWith(otherPiece);
        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Piece의 색깔을 체크한다.")
    void hasColorOf() {
        // given
        Piece piece = new King(Color.WHITE);
        // when
        boolean result = piece.hasColorOf(Color.WHITE);
        // then
        assertThat(result).isTrue();
    }
}
