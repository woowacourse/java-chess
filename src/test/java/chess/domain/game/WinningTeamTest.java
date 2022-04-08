package chess.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningTeamTest {

    @Test
    @DisplayName("백팀 접수가 더 높으면 백팀이 승리한다.")
    void winningTeam_WhenWhiteScoreIsBigger() {
        WinningTeam winningTeam = new WinningTeam(10, 20, Team.NONE);

        assertThat(winningTeam.getValue()).isEqualTo("WHITE");
    }

    @Test
    @DisplayName("흑팀 접수가 더 높거나 같으면 흑팀이 승리한다.")
    void winningTeam_WhenBlackScoreIsBigger() {
        WinningTeam winningTeam = new WinningTeam(20, 10, Team.NONE);

        assertThat(winningTeam.getValue()).isEqualTo("BLACK");
    }

    @Test
    @DisplayName("흑팀 왕이 죽으면 백팀이 승리한다.")
    void winningTeam_WhenBlackKingDead() {
        WinningTeam winningTeam = new WinningTeam(20, 10, Team.BLACK);

        assertThat(winningTeam.getValue()).isEqualTo("WHITE");
        assertThat(winningTeam.getWinningType()).isEqualTo("Win By Slaying King");
    }

    @Test
    @DisplayName("백팀 왕이 죽으면 흑팀이 승리한다.")
    void winningTeam_WhenWhiteKingDead() {
        WinningTeam winningTeam = new WinningTeam(10, 20, Team.WHITE);

        assertThat(winningTeam.getValue()).isEqualTo("BLACK");
        assertThat(winningTeam.getWinningType()).isEqualTo("Win By Slaying King");
    }

    @Test
    @DisplayName("왕을 죽음으로서 승리한것인지 판별한다.")
    void isWinByKingDead() {
        WinningTeam winningTeam = new WinningTeam(20, 10, Team.BLACK);

        assertThat(winningTeam.isWinByKingDead()).isTrue();
    }
}