package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {

    @Test
    @DisplayName("적이면 적으로 인식한다.")
    void is_enemy() {
        Piece whitePiece = new Pawn(Team.WHITE);
        Piece blackPiece = new Pawn(Team.BLACK);
        assertThat(whitePiece.isEnemy(blackPiece)).isTrue();
    }

    @Test
    @DisplayName("적이 아니면 적으로 인식하지 않는다.")
    void is_not_enemy() {
        Piece firstWhitePiece = new Pawn(Team.WHITE);
        Piece secondWhitePiece = new Pawn(Team.WHITE);
        assertThat(firstWhitePiece.isEnemy(secondWhitePiece)).isFalse();
    }

    @Test
    @DisplayName("폰이면 true를 반환한다.")
    void is_pawn_true() {
        Piece pawn = new Pawn(Team.WHITE);
        assertThat(pawn.isPawn()).isTrue();
    }

    @Test
    @DisplayName("폰이 아니면 false를 반환한다.")
    void is_pawn_false() {
        Piece rook = new Rook(Team.WHITE);
        assertThat(rook.isPawn()).isFalse();
    }

    @Test
    @DisplayName("킹이면 true를 반환한다.")
    void is_king_true() {
        Piece king = new King(Team.WHITE);
        assertThat(king.isKing()).isTrue();
    }

    @Test
    @DisplayName("킹이 아니면 false를 반환한다.")
    void is_king_false() {
        Piece bishop = new Bishop(Team.WHITE);
        assertThat(bishop.isKing()).isFalse();
    }
}
