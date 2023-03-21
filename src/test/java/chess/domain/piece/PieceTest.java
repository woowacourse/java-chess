package chess.domain.piece;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Move;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PieceTest {

    private static class PieceImplement extends Piece {

        public PieceImplement(Color color) {
            super(color);
        }

        @Override
        public boolean isValidMove(Move move, Piece targetPiece) {
            return false;
        }

        @Override
        public PieceType getType() {
            return null;
        }
    }

    @DisplayName("공격 가능한 상대인지 판단한다")
    @Test
    void isRightTarget() {
        Piece piece = new PieceImplement(WHITE);
        Piece otherPiece = new PieceImplement(BLACK);

        assertThat(piece.isRightTarget(otherPiece)).isTrue();
    }

    @DisplayName("공격 불가능한 상대인지 판단한다")
    @Test
    void isNotRightTarget() {
        Piece piece = new PieceImplement(WHITE);
        Piece otherPiece = new PieceImplement(WHITE);

        assertThat(piece.isRightTarget(otherPiece)).isFalse();
    }

    @DisplayName("색을 확인할 수 있다.")
    @Test
    void hasColor() {
        Piece piece = new PieceImplement(WHITE);

        assertThat(piece.isRightTurn(WHITE)).isTrue();
        assertThat(piece.isRightTurn(BLACK)).isFalse();
    }

    @DisplayName("기본적으로 Touch 시 상태가 변하지 않는다")
    @Test
    void touch_nothingHappens() {
        Piece piece = new PieceImplement(WHITE);

        assertThat(piece.touch()).isSameAs(piece);
    }
}
