package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PieceTest {
    @Test
    @DisplayName("두 동일한 객체를 가져왔을 때 같은지 확인")
    void checkSameInstance() {
        Piece piece = PieceImpl.of(Color.BLACK, Type.KING);
        assertThat(piece).isEqualTo(PieceImpl.of(Color.BLACK, Type.KING));
    }

    @Test
    @DisplayName("null check")
    void nullTest() {
        assertThatThrownBy(() -> PieceImpl.of(null, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("잘못된 입력입니다");
    }

    @Test
    @DisplayName("체스 말이 블랙인지 검증하는 테스트")
    void isBlack() {
        assertThat(PieceImpl.of(Color.BLACK, Type.PAWN).isBlack()).isTrue();
        assertThat(PieceImpl.of(Color.WHITE, Type.KING).isBlack()).isFalse();
    }
}
