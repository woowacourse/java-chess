package chess.domain.piece.constant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import chess.domain.piece.Piece;
import chess.domain.piece.movable.multiple.Queen;

class PromotablePieceTest {

    @DisplayName("프로모션 가능한 기물 이외의 이름은 해당 기물로 변환할 수 없어야 합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"a", "1", "queen1", "rooka"})
    void noSuchPromotablePieceNameException(final String pieceName) {
        assertThatThrownBy(() ->PromotablePiece.convertToPromotablePiece(pieceName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("폰으로 프로모션할 수 있는 기물의 이름이 아닙니다.");
    }

    @DisplayName("프로모션 가능한 기물 중 이름에 해당하는 기물로 변환할 수 있어야 합니다.")
    @Test
    void convertToPromotablePiece() {
        final String pieceName = "Queen";
        final Piece piece = PromotablePiece.convertToPromotablePiece(pieceName);
        assertThat(piece).isInstanceOf(Queen.class);
    }
}