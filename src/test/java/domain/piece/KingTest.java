package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import chess.domain.TeamColor;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KingTest {

    @Test
    @DisplayName("객체 생성")
    void create() {
        assertThatCode(() -> new King(TeamColor.BLACK)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("블랙팀 객체 이름출력")
    void ValidBlackKingName() {
        Piece king = new King(TeamColor.BLACK);
        assertThat(king.getPieceName()).isEqualTo("K");
    }

    @Test
    @DisplayName("화이트팀 객체 이름출력")
    void ValidWhiteKingName() {
        Piece king = new King(TeamColor.WHITE);
        assertThat(king.getPieceName()).isEqualTo("k");
    }


}
