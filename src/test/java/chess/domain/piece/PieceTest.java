package chess.domain.piece;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.game.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PieceTest {

    private static class PieceImplement extends Piece {

        public PieceImplement(Color color) {
            super(color, PieceType.BISHOP);
        }

        @Override
        public PieceType getType() {
            return null;
        }
    }

    @DisplayName("같은 색상의 기물인지 판단한다")
    @Test
    void isSameColor() {
        Piece piece = new PieceImplement(WHITE);
        Piece otherPiece = new PieceImplement(WHITE);

        assertThat(piece.isSameColor(otherPiece)).isTrue();
    }

    @DisplayName("다른 색상의 기물인지 판단한다")
    @Test
    void isNotSameColor() {
        Piece piece = new PieceImplement(WHITE);
        Piece otherPiece = new PieceImplement(BLACK);

        assertThat(piece.isSameColor(otherPiece)).isFalse();
    }

    @DisplayName("색을 확인할 수 있다.")
    @Test
    void hasColor() {
        Piece piece = new PieceImplement(WHITE);

        assertThat(piece.isRightTurn(WHITE)).isTrue();
        assertThat(piece.isRightTurn(BLACK)).isFalse();
    }

    @DisplayName("점수를 확인할 수 있다")
    @Test
    void getValue() {
        Piece piece = new PieceImplement(WHITE);

        assertThat(piece.getScore()).isEqualTo(Score.valueOf(3));
    }
}
