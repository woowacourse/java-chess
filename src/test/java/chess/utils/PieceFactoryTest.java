package chess.utils;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceFactoryTest {

    @Test
    @DisplayName("이름으로 Piece를 생성한다.")
    void convertToPiece() {
        Piece piece = PieceFactory.convertToPiece("white_pawn");
        Assertions.assertThat(piece.isSamePieceType(PieceType.PAWN)
                && piece.isSameColor(Color.WHITE)).isTrue();
    }

    @Test
    @DisplayName("PieceName이 잘못 들어온 경우 예외가 발생한다.")
    void convertToPieceException() {
        Assertions.assertThatThrownBy(() -> PieceFactory.convertToPiece("white"))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("잘못된 pieceName이 들어왔습니다.");
    }
}
