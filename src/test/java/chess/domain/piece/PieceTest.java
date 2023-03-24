package chess.domain.piece;

import chess.domain.Color;
import chess.domain.PieceType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {
    @DisplayName("기물이 동일한 색상인지 확인하는 테스트")
    @Test
    void isSameColorTest() {
        // given & when
        final Piece pawn = Pawn.from(Color.WHITE);

        // then
        assertThat(pawn.isSameColor(Color.WHITE)).isTrue();
    }

    @DisplayName("기물이 동일한 색상이 아닌지 확인하는 테스트")
    @Test
    void isNotSameColor() {
        // given
        final Piece blackPawn = Pawn.from(Color.BLACK);

        // when
        final boolean isNotSameColor = blackPawn.isNotSameColor(Color.WHITE);

        // then
        assertThat(isNotSameColor).isTrue();
    }

    @DisplayName("기물이 동일한 기물 타입인지 확인하는 테스트")
    @Test
    void isSamePieceType() {
        // given
        final Piece pawn = Pawn.from(Color.BLACK);

        // when
        final boolean isSamePieceType = pawn.isSamePieceType(PieceType.PAWN);

        // then
        assertThat(isSamePieceType).isTrue();
    }

    @DisplayName("기물이 동일한 기물 타입이 아닌지 확인하는 테스트")
    @Test
    void isNotSamePieceType() {
        // given
        final Piece pawn = Pawn.from(Color.BLACK);

        // when
        final boolean isNotSamePieceType = pawn.isNotSamePieceType(PieceType.KING);

        // then
        assertThat(isNotSamePieceType).isTrue();
    }
}
