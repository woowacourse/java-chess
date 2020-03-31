package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {
    @DisplayName("팀 별 대소문자로 출력")
    @Test
    void toSymbol() {
        Piece whitePiece = new Bishop(PieceType.BISHOP, Team.WHITE);
        Piece blackPiece = new Bishop(PieceType.BISHOP, Team.BLACK);
        assertThat(whitePiece.toSymbol()).isEqualTo("b");
        assertThat(blackPiece.toSymbol()).isEqualTo("B");
    }

    @DisplayName("피스의 팀 구분")
    @Test
    void isEnemy() {
        Piece whitePiece = new Knight(PieceType.KNIGHT, Team.WHITE);
        Piece blackPiece = new Knight(PieceType.KNIGHT, Team.BLACK);
        assertThat(whitePiece.isEnemy(blackPiece)).isTrue();
    }

    @DisplayName("피스가 흰 팀의 킹인지 테스트")
    @Test
    void isWhiteKing() {
        Piece whiteKing = new King(PieceType.KING, Team.WHITE);
        Piece blackBishop = new Bishop(PieceType.BISHOP, Team.BLACK);
        assertThat(whiteKing.isWhiteKing()).isTrue();
        assertThat(blackBishop.isWhiteKing()).isFalse();
    }

    @DisplayName("피스가 검은 팀의 킹인지 테스트")
    @Test
    void isBlackKing() {
        Piece blackKing = new King(PieceType.KING, Team.BLACK);
        Piece whiteBishop = new Bishop(PieceType.BISHOP, Team.WHITE);
        assertThat(blackKing.isBlackKing()).isTrue();
        assertThat(whiteBishop.isBlackKing()).isFalse();
    }

    @DisplayName("피스가 폰인지 테스트")
    @Test
    void isPawn() {
        Piece whitePawn = new Pawn(PieceType.PAWN, Team.WHITE);
        Piece blackQueen = new Queen(PieceType.QUEEN, Team.BLACK);
        assertThat(whitePawn.isPawn()).isTrue();
        assertThat(blackQueen.isPawn()).isFalse();
    }
}