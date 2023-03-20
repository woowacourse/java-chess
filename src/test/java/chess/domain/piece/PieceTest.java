package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.TeamColor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class PieceTest {

    @DisplayName("Piece 가 요청과 다른 색이면 true 가 반환된다.")
    @Test
    void 다른_색_체스말_확인() {
        King whiteKing = new King(TeamColor.WHITE);

        assertThat(whiteKing.isDifferentColor(TeamColor.BLACK)).isTrue();
    }

    @DisplayName("Piece 가 요청과 같은 색이면 true 가 반환된다.")
    @Test
    void 같은_색_체스말_확인() {
        King whiteKing = new King(TeamColor.WHITE);

        assertThat(whiteKing.isSameColor(TeamColor.WHITE)).isTrue();
    }

}