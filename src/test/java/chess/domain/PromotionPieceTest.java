package chess.domain;

import static chess.domain.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Piece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PromotionPieceTest {

    @Test
    @DisplayName("Promotion 기물 생성 시 null입력 예외발생")
    void createPromotionPieceNullException() {
        assertThatThrownBy(() -> PromotionPiece.createPromotionPiece(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("input은 null이 들어올 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"q", "r", "p", "k"})
    @DisplayName("Promotion 기물 생성 시 잘못된 입력값이 들어올 경우 예외발생")
    void cretePromotionPieceException(String input) {
        assertThatThrownBy(() -> PromotionPiece.createPromotionPiece(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("불가능한 프로모션 기물 이름입니다.");
    }

    @Test
    @DisplayName("입력값에 따른 기물 생성")
    void createPromotionPiece() {
        PromotionPiece promotionPiece = PromotionPiece.createPromotionPiece("N");
        assertThat(promotionPiece).isEqualTo(PromotionPiece.KNIGHT);
    }

    @Test
    @DisplayName("색상을 받아 piece 생성")
    void createPiece() {
        PromotionPiece promotionPiece = PromotionPiece.createPromotionPiece("N");
        Piece piece = promotionPiece.createPiece(WHITE);

        assertThat(piece).isInstanceOf(Piece.class);
    }
}
