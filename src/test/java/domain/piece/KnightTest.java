package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import chess.domain.TeamColor;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KnightTest {

    @Test
    @DisplayName("객체 생성")
    void create() {
        assertThatCode(() -> new Knight(TeamColor.BLACK)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("블랙팀 객체 이름출력")
    void ValidBlackKnightName() {
        Piece knight = new Knight(TeamColor.BLACK);
        assertThat(knight.getPieceName()).isEqualTo("N");
    }

    @Test
    @DisplayName("화이트팀 객체 이름출력")
    void ValidWhiteKnightName() {
        Piece knight = new Knight(TeamColor.WHITE);
        assertThat(knight.getPieceName()).isEqualTo("n");
    }
}
