package chess.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.attribute.Team;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultGameTest {

    @Test
    @DisplayName("각 진영중 킹을 잡은 진영인 화이트가 승리한다.")
    void calculateBlackWinnerStatus() {
        Map<Team, Double> colorDoubleMap = new HashMap<>();
        colorDoubleMap.put(Team.BLACK, 27.0);
        colorDoubleMap.put(Team.WHITE, 28.0);

        Status status = new Status(colorDoubleMap, Team.WHITE);

        assertThat(status.getWinner()).isEqualTo(Team.WHITE);
    }


    @Test
    @DisplayName("각 진영중 킹을 잡은 진영인 블랙이 승리한다.")
    void calculateWhiteWinnerStatus() {
        Map<Team, Double> colorDoubleMap = new HashMap<>();
        colorDoubleMap.put(Team.BLACK, 26.0);
        colorDoubleMap.put(Team.WHITE, 27.0);

        Status status = new Status(colorDoubleMap, Team.BLACK);

        assertThat(status.getWinner()).isEqualTo(Team.BLACK);
    }
}
