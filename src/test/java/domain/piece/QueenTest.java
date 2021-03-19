package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import chess.domain.TeamColor;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QueenTest {

    @Test
    @DisplayName("객체 생성")
    void create() {
        assertThatCode(() -> new Knight(TeamColor.BLACK)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("블랙팀 객체 이름출력")
    void ValidBlackQueenName() {
        Piece queen = new Queen(TeamColor.BLACK);
        assertThat(queen.getPieceName()).isEqualTo("Q");
    }

    @Test
    @DisplayName("화이트팀 객체 이름출력")
    void ValidWhiteQueenName() {
        Piece queen = new Queen(TeamColor.WHITE);
        assertThat(queen.getPieceName()).isEqualTo("q");
    }
}
