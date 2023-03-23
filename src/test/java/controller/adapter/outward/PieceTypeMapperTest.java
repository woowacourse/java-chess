package controller.adapter.outward;

import domain.piece.Color;
import domain.piece.nonsliding.King;
import domain.piece.nonsliding.Knight;
import domain.piece.pawn.BlackPawn;
import domain.piece.pawn.Pawn;
import domain.piece.pawn.WhitePawn;
import domain.piece.sliding.Bishop;
import domain.piece.sliding.Queen;
import domain.piece.sliding.Rook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PieceTypeMapperTest {

    @Test
    @DisplayName("폰인 경우, p를 반환한다")
    void getTargetPawn() {
        Pawn whitePawn = new WhitePawn(Color.WHITE);
        Pawn blackPawn = new BlackPawn(Color.BLACK);

        assertThat(PieceTypeMapper.getTarget(whitePawn)).isEqualTo("p");
        assertThat(PieceTypeMapper.getTarget(blackPawn)).isEqualTo("P");
    }

    @Test
    @DisplayName("룩인 경우, r를 반환한다")
    void getTargetRook() {
        Rook whiteRook = new Rook(Color.WHITE);
        Rook blackRook = new Rook(Color.BLACK);

        assertThat(PieceTypeMapper.getTarget(whiteRook)).isEqualTo("r");
        assertThat(PieceTypeMapper.getTarget(blackRook)).isEqualTo("R");
    }

    @Test
    @DisplayName("퀸인 경우, q를 반환한다")
    void getTargetQueen() {
        Queen whiteQueen = new Queen(Color.WHITE);
        Queen blackQueen = new Queen(Color.BLACK);

        assertThat(PieceTypeMapper.getTarget(whiteQueen)).isEqualTo("q");
        assertThat(PieceTypeMapper.getTarget(blackQueen)).isEqualTo("Q");
    }

    @Test
    @DisplayName("비숍인 경우, b를 반환한다")
    void getTargetBishop() {
        Bishop whiteBishop = new Bishop(Color.WHITE);
        Bishop blackBishop = new Bishop(Color.BLACK);

        assertThat(PieceTypeMapper.getTarget(whiteBishop)).isEqualTo("b");
        assertThat(PieceTypeMapper.getTarget(blackBishop)).isEqualTo("B");
    }

    @Test
    @DisplayName("나이트인 경우, n를 반환한다")
    void getTargetKnight() {
        Knight whiteKnight = new Knight(Color.WHITE);
        Knight blackKnight = new Knight(Color.BLACK);

        assertThat(PieceTypeMapper.getTarget(whiteKnight)).isEqualTo("n");
        assertThat(PieceTypeMapper.getTarget(blackKnight)).isEqualTo("N");
    }

    @Test
    @DisplayName("킹인 경우, k를 반환한다")
    void getTargetKing() {
        King whiteKing = new King(Color.WHITE);
        King blackKing = new King(Color.BLACK);

        assertThat(PieceTypeMapper.getTarget(whiteKing)).isEqualTo("k");
        assertThat(PieceTypeMapper.getTarget(blackKing)).isEqualTo("K");
    }
}
