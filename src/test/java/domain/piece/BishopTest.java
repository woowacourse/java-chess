package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import chess.domain.TeamColor;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BishopTest {

    @Test
    @DisplayName("객체 생성")
    void create() {
        assertThatCode(() -> new King(TeamColor.BLACK)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("블랙팀 객체 이름출력")
    void ValidBlackBishopName() {
        Piece bishop = new Bishop(TeamColor.BLACK);
        assertThat(bishop.getPieceName()).isEqualTo("B");
    }

    @Test
    @DisplayName("화이트팀 객체 이름출력")
    void ValidWhiteBishopName() {
        Piece bishop = new Bishop(TeamColor.WHITE);
        assertThat(bishop.getPieceName()).isEqualTo("b");
    }
}
