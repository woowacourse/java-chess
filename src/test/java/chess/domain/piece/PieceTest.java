package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static chess.domain.piece.PositionTexture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PieceTest {
    @DisplayName("Piece 정상 생성 테스트.")
    @Test
    void createPiece() {
        String blackK = "K";
        Piece blackKingPiece = King.from(blackK, A1);
        assertThat(blackKingPiece).isInstanceOf(King.class);

        String blackP = "P";
        Piece blackPawnPiece = Pawn.from(blackP, B2);
        assertThat(blackPawnPiece).isInstanceOf(Pawn.class);

        String blackQ = "Q";
        Piece blackQueenPiece = Queen.from(blackQ, C3);
        assertThat(blackQueenPiece).isInstanceOf(Queen.class);

        String blackN = "N";
        Piece blackKnightPiece = Knight.from(blackN, D4);
        assertThat(blackKnightPiece).isInstanceOf(Knight.class);

        String blackB = "B";
        Piece blackBishopPiece = Bishop.from(blackB, E5);
        assertThat(blackBishopPiece).isInstanceOf(Bishop.class);

        String blackR = "R";
        Piece blackRookPiece = Rook.from(blackR, F6);
        assertThat(blackRookPiece).isInstanceOf(Rook.class);
    }

    @DisplayName("Piece 생성 예외 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"a", "C", "D", "e", "검프", "다니"})
    void createPieceException(String piece) {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            Bishop.from(piece, A1);
        }).withMessage("옳지 않은 기물입니다! 입력 값: %s", piece);

        assertThatIllegalArgumentException().isThrownBy(() -> {
            King.from(piece, B2);
        }).withMessage("옳지 않은 기물입니다! 입력 값: %s", piece);

        assertThatIllegalArgumentException().isThrownBy(() -> {
            Knight.from(piece, C3);
        }).withMessage("옳지 않은 기물입니다! 입력 값: %s", piece);

        assertThatIllegalArgumentException().isThrownBy(() -> {
            Pawn.from(piece, D4);
        }).withMessage("옳지 않은 기물입니다! 입력 값: %s", piece);

        assertThatIllegalArgumentException().isThrownBy(() -> {
            Queen.from(piece, E5);
        }).withMessage("옳지 않은 기물입니다! 입력 값: %s", piece);

        assertThatIllegalArgumentException().isThrownBy(() -> {
            Rook.from(piece, F6);
        }).withMessage("옳지 않은 기물입니다! 입력 값: %s", piece);
    }

    @DisplayName("기물이 생성될 때, 위치값도 함께 초기화된다.")
    @Test
    void piecePositionTest() {
        assertThat(Bishop.from("b", B2).isSamePosition(B2)).isEqualTo(true);
        assertThat(King.from("k", B2).isSamePosition(B2)).isEqualTo(true);
        assertThat(Knight.from("n", B2).isSamePosition(B2)).isEqualTo(true);
        assertThat(Pawn.from("p", B2).isSamePosition(B2)).isEqualTo(true);
        assertThat(Queen.from("q", B2).isSamePosition(B2)).isEqualTo(true);
        assertThat(Rook.from("r", B2).isSamePosition(B2)).isEqualTo(true);
    }
}
