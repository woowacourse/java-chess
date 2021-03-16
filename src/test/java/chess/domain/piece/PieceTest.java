package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {

    @DisplayName("Piece 정상 생성 테스트.")
    @Test
    void createPiece() {
        String blackK = "K";
        Piece blackKingPiece = King.from(blackK);
        assertThat(blackKingPiece).isInstanceOf(King.class);

        String blackP = "P";
        Piece blackPawnPiece = Pawn.from(blackP);
        assertThat(blackPawnPiece).isInstanceOf(Pawn.class);

        String blackQ = "Q";
        Piece blackQueenPiece = Queen.from(blackQ);
        assertThat(blackQueenPiece).isInstanceOf(Queen.class);

        String blackN = "N";
        Piece blackKnightPiece = Knight.from(blackN);
        assertThat(blackKnightPiece).isInstanceOf(Knight.class);

        String blackB = "B";
        Piece blackBishopPiece = Bishop.from(blackB);
        assertThat(blackBishopPiece).isInstanceOf(Bishop.class);

        String blackR = "R";
        Piece blackRookPiece = Rook.from(blackR);
        assertThat(blackRookPiece).isInstanceOf(Rook.class);
    }
}
