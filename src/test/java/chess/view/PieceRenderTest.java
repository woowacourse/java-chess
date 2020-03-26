package chess.view;

import chess.board.piece.King;
import chess.board.piece.Piece;
import chess.board.piece.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class PieceRenderTest {

    @DisplayName("피스에 따라 뷰에 보여지는 토큰을 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"BLACK,K", "WHITE,k"})
    void findTokenByPiece(Team team, String expect) {
        //given
        Piece piece = new King(team);

        //when
        String actual = PieceRender.findTokenByPiece(piece);

        //then
        assertThat(actual).isEqualTo(expect);
    }

}