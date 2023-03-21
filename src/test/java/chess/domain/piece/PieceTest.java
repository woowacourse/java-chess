package chess.domain.piece;

import chess.domain.piece.pawn.BlackPawn;
import chess.domain.piece.pawn.WhitePawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {

    @Test
    @DisplayName("적이면 적으로 인식한다.")
    void is_enemy() {
        Piece whitePiece = new WhitePawn();
        Piece blackPiece = new BlackPawn();

        assertThat(whitePiece.isEnemy(blackPiece)).isTrue();
    }

    @Test
    @DisplayName("적이 아니면 적으로 인식하지 않는다.")
    void is_not_enemy() {
        Piece firstWhitePiece = new WhitePawn();
        Piece secondWhitePiece = new WhitePawn();

        assertThat(firstWhitePiece.isEnemy(secondWhitePiece)).isFalse();
    }

    @Test
    @DisplayName("폰이면 true를 반환한다.")
    void is_pawn_true() {
        Piece pawn = new WhitePawn();

        assertThat(pawn.isPawn()).isTrue();
    }

    @Test
    @DisplayName("폰이 아니면 false를 반환한다.")
    void is_pawn_false() {
        Piece rook = new Rook(Color.WHITE);

        assertThat(rook.isPawn()).isFalse();
    }
}