package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceFactoryTest {

    @DisplayName("검정색 진영의 Pawn 타입의 Piece 인스턴스를 생성한다.")
    @Test
    void createPawn_withBlackColor() {
        // given
        Piece blackPawn = PieceFactory.createPawn(PieceColor.BLACK);

        // when & then
        assertThat(blackPawn.getPieceType()).isEqualTo(PieceType.PAWN);
    }

    @DisplayName("검정색 진영의 Rook 타입의 Piece 인스턴스를 생성한다.")
    @Test
    void createRook_withBlackColor() {
        // given
        Piece blackRook = PieceFactory.createRook(PieceColor.BLACK);

        // when & then
        assertThat(blackRook.getPieceType()).isEqualTo(PieceType.ROOK);
    }

    @DisplayName("검정색 진영의 Night 타입의 Piece 인스턴스를 생성한다.")
    @Test
    void createNight_withBlackColor() {
        // given
        Piece blackKnight = PieceFactory.createNight(PieceColor.BLACK);

        // when & then
        assertThat(blackKnight.getPieceType()).isEqualTo(PieceType.KNIGHT);
    }

    @DisplayName("검정색 진영의 Bishop 타입의 Piece 인스턴스를 생성한다.")
    @Test
    void createBishop_withBlackColor() {
        // given
        Piece blackBishop = PieceFactory.createBishop(PieceColor.BLACK);

        // when & then
        assertThat(blackBishop.getPieceType()).isEqualTo(PieceType.BISHOP);
    }

    @DisplayName("검정색 진영의 Queen 타입의 Piece 인스턴스를 생성한다.")
    @Test
    void createQueen_withBlackColor() {
        // given
        Piece blackQueen = PieceFactory.createQueen(PieceColor.BLACK);

        // when & then
        assertThat(blackQueen.getPieceType()).isEqualTo(PieceType.QUEEN);
    }

    @DisplayName("검정색 진영의 King 타입의 Piece 인스턴스를 생성한다.")
    @Test
    void createKing_withBlackColor() {
        // given
        Piece blackKing = PieceFactory.createKing(PieceColor.BLACK);

        // when & then
        assertThat(blackKing.getPieceType()).isEqualTo(PieceType.KING);
    }
}
