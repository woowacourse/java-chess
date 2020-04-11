package chess.consoleView;

import chess.consolView.PieceRender;
import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class PieceRenderTest {

    @DisplayName("피스에 따라 뷰에 보여지는 토큰을 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"BLACK_KING,♚", "WHITE_KING,♔", "BLACK_MOVED_PAWN,♟", "BLACK_NOT_MOVED_PAWN,♟", "WHITE_MOVED_PAWN,♙", "WHITE_NOT_MOVED_PAWN,♙"})
    void findTokenByPiece(Pieces pieces, String expect) {
        //given
        Piece piece = pieces.getPiece();

        //when
        String actual = PieceRender.findTokenByPiece(piece);

        //then
        assertThat(actual).isEqualTo(expect);
    }
}