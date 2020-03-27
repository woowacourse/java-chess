package chess.domain.piece;

import chess.domain.ChessRunner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TeamTest {
    @DisplayName("팀 순서 변경 테스트")
    @Test
    void changeTeam() {
        ChessRunner chessRunner = new ChessRunner();
        Team nextTeam = Team.changeTeam(chessRunner.getCurrentTeam());
        assertThat(nextTeam.isSameTeamWith(Team.BLACK)).isTrue();
    }
}