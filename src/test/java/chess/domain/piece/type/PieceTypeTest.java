package chess.domain.piece.type;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.player.type.TeamColor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTypeTest {
    @DisplayName("흑의 기물들은 대문자로 표기하고, 백의 기물들은 소문자로 표기한다.")
    @Test
    void pieceName() {
        assertThat(PieceType.KING.getName(TeamColor.BLACK)).isEqualTo("K");
        assertThat(PieceType.KING.getName(TeamColor.WHITE)).isEqualTo("k");
    }
}