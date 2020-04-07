package chess.consolView;

import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class PieceRenderTest {

    @DisplayName("피스에 따라 뷰에 보여지는 토큰을 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"BLACK_KING,K", "WHITE_KING,k", "BLACK_MOVED_PAWN,P", "BLACK_NOT_MOVED_PAWN,P", "WHITE_MOVED_PAWN,p", "WHITE_NOT_MOVED_PAWN,p"})
    void findTokenByPiece(Pieces pieces, String expect) {
        //given
        Piece piece = pieces.getPiece();

        //when
        String actual = PieceRender.findTokenByPiece(piece);

        //then
        assertThat(actual).isEqualTo(expect);
    }
}