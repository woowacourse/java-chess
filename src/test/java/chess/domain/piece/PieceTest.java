package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {

    @Test
    @DisplayName("적이면 적으로 인식한다.")
    void is_enemy() {
        Piece whitePiece = new Pawn(Color.WHITE);
        Piece blackPiece = new Pawn(Color.BLACK);
        assertThat(whitePiece.isEnemy(blackPiece)).isTrue();
    }

    @Test
    @DisplayName("적이 아니면 적으로 인식하지 않는다.")
    void is_not_enemy() {
        Piece firstWhitePiece = new Pawn(Color.WHITE);
        Piece secondWhitePiece = new Pawn(Color.WHITE);
        assertThat(firstWhitePiece.isEnemy(secondWhitePiece)).isFalse();
    }

    @Test
    @DisplayName("폰이면 true를 반환한다.")
    void is_pawn_true() {
        Piece pawn = new Pawn(Color.WHITE);
        assertThat(pawn.isPawn()).isTrue();
    }

    @Test
    @DisplayName("폰이 아니면 false를 반환한다.")
    void is_pawn_false() {
        Piece rook = new Rook(Color.WHITE);
        System.out.println(rook.getName());
        assertThat(rook.isPawn()).isFalse();
    }
}
