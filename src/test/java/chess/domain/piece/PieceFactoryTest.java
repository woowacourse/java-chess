package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceFactoryTest {

    @DisplayName("검정색 진영의 Pawn 인스턴스를 생성한다.")
    @Test
    void createPawn_withBlackColor() {
        // given
        Pawn blackPawn = PieceFactory.createPawn(PieceColor.BLACK);

        // when & then
        assertThat(blackPawn).isInstanceOf(Pawn.class);
    }

    @DisplayName("검정색 진영의 Rook 인스턴스를 생성한다.")
    @Test
    void createRook_withBlackColor() {
        // given
        Rook blackRook = PieceFactory.createRook(PieceColor.BLACK);

        // when & then
        assertThat(blackRook).isInstanceOf(Rook.class);
    }

    @DisplayName("검정색 진영의 Night 인스턴스를 생성한다.")
    @Test
    void createNight_withBlackColor() {
        // given
        Knight blackKnight = PieceFactory.createNight(PieceColor.BLACK);

        // when & then
        assertThat(blackKnight).isInstanceOf(Knight.class);
    }

    @DisplayName("검정색 진영의 Bishop 인스턴스를 생성한다.")
    @Test
    void createBishop_withBlackColor() {
        // given
        Bishop blackBishop = PieceFactory.createBishop(PieceColor.BLACK);

        // when & then
        assertThat(blackBishop).isInstanceOf(Bishop.class);
    }

    @DisplayName("검정색 진영의 Queen 인스턴스를 생성한다.")
    @Test
    void createQueen_withBlackColor() {
        // given
        Queen blackQueen = PieceFactory.createQueen(PieceColor.BLACK);

        // when & then
        assertThat(blackQueen).isInstanceOf(Queen.class);
    }

    @DisplayName("검정색 진영의 King 인스턴스를 생성한다.")
    @Test
    void createKing_withBlackColor() {
        // given
        King blackKing = PieceFactory.createKing(PieceColor.BLACK);

        // when & then
        assertThat(blackKing).isInstanceOf(King.class);
    }

}