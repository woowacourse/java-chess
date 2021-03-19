package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import chess.domain.TeamColor;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PawnTest {

    @Test
    @DisplayName("객체 생성")
    void create() {
        assertThatCode(() -> new Knight(TeamColor.BLACK)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("블랙팀 객체 이름출력")
    void ValidBlackPawnName() {
        Piece pawn = new Pawn(TeamColor.BLACK);
        assertThat(pawn.getPieceName()).isEqualTo("P");
    }

    @Test
    @DisplayName("화이트팀 객체 이름출력")
    void ValidWhitePawnName() {
        Piece pawn = new Pawn(TeamColor.WHITE);
        assertThat(pawn.getPieceName()).isEqualTo("p");
    }
}
