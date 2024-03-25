package chess.domain.player;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @ParameterizedTest
    @CsvSource(value = {"BLACK, true", "WHITE, false"})
    @DisplayName("움직일 기물이 자신 팀의 기물인지 판별할 수 있다.")
    void isMyPiece(Team team, boolean expected) {
        Piece pawn = new Pawn(team);
        Player player = new Player(Team.BLACK);

        boolean result = player.isMyPiece(pawn);

        assertThat(result).isEqualTo(expected);
    }
}
