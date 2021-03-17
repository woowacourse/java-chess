package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

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

    @DisplayName("Piece 생성 예외 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"a", "C", "D", "e", "검프", "다니"})
    void createPieceException(String piece) {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            Bishop.from(piece);
        }).withMessage("옳지 않은 기물입니다! 입력 값: %s", piece);

        assertThatIllegalArgumentException().isThrownBy(() -> {
            King.from(piece);
        }).withMessage("옳지 않은 기물입니다! 입력 값: %s", piece);

        assertThatIllegalArgumentException().isThrownBy(() -> {
            Knight.from(piece);
        }).withMessage("옳지 않은 기물입니다! 입력 값: %s", piece);

        assertThatIllegalArgumentException().isThrownBy(() -> {
            Pawn.from(piece);
        }).withMessage("옳지 않은 기물입니다! 입력 값: %s", piece);

        assertThatIllegalArgumentException().isThrownBy(() -> {
            Queen.from(piece);
        }).withMessage("옳지 않은 기물입니다! 입력 값: %s", piece);

        assertThatIllegalArgumentException().isThrownBy(() -> {
            Rook.from(piece);
        }).withMessage("옳지 않은 기물입니다! 입력 값: %s", piece);
    }
}
