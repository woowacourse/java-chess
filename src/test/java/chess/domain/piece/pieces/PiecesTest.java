package chess.domain.piece.pieces;

import chess.domain.piece.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PiecesTest {

    @DisplayName("isKingDead 모든 킹이 살아있다면 false 반환")
    @Test
    void isKingDead_all_king_alive_return_false() {
        Pieces pieces = PiecesFactory.create();
        assertThat(pieces.isKingDead()).isFalse();
    }

    @DisplayName("isKingDead 킹이 하나만 살아있다면 true 반환")
    @Test
    void isKingDead_one_king_alive_return_true() {
        Pieces pieces = TestPiecesFactory.createOnlyWhite();
        assertThat(pieces.isKingDead()).isTrue();
    }

    @DisplayName("isKingDead 하얀 킹 하나만 살아있다면 WHITE 반환")
    @Test
    void getAliveKingColor_white_king_alive_return_white() {
        Pieces pieces = TestPiecesFactory.createOnlyWhite();
        assertThat(pieces.getAliveKingColor()).isEqualTo(Color.WHITE);
    }
}