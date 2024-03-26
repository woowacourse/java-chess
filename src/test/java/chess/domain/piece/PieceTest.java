package chess.domain.piece;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.slidingpiece.Rook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {

    @Test
    @DisplayName("WHITE의 말인지 여부 확인")
    void isWhite() {
        Rook rook = new Rook(WHITE);
        assertThat(rook.isWhite()).isTrue();
    }

    @Test
    @DisplayName("반대 색을 불러올 수 있다.")
    void hasOppositeColorFrom() {
        Piece whitePawn = new Pawn(WHITE);
        Piece blackPawn = new Pawn(BLACK);
        Assertions.assertAll(
            () -> assertThat(whitePawn.hasOppositeColorFrom(blackPawn)).isTrue(),
            () -> assertThat(blackPawn.hasOppositeColorFrom(whitePawn)).isTrue()
        );
    }
}
