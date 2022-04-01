package chess.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.attribute.Color;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StatusTest {

    @Test
    @DisplayName("각 진영중 하얀색 진영의 기물 점수의 총합이 검은색 진영보다 높을 경우 하얀색이 승리한다.")
    void calculateBlackWinnerStatus() {
        Map<Color, Double> colorDoubleMap = new HashMap<>();
        colorDoubleMap.put(Color.BLACK, 27.0);
        colorDoubleMap.put(Color.WHITE, 28.0);

        Status status = new Status(colorDoubleMap);

        assertThat(status.getWinnerColor()).isEqualTo(Color.WHITE);
    }


    @Test
    @DisplayName("각 진영중 검은색 진영의 기물 점수의 총합이 하얀색 진영보다 높을 경우 검은색이 승리한다.")
    void calculateWhiteWinnerStatus() {
        Map<Color, Double> colorDoubleMap = new HashMap<>();
        colorDoubleMap.put(Color.BLACK, 28.0);
        colorDoubleMap.put(Color.WHITE, 27.0);

        Status status = new Status(colorDoubleMap);

        assertThat(status.getWinnerColor()).isEqualTo(Color.BLACK);
    }

    @Test
    @DisplayName("각 진영의 기물 점수의 총합이 같은 경우 결과는 무승부이다.")
    void calculateDrawStatus() {
        Map<Color, Double> colorDoubleMap = new HashMap<>();
        colorDoubleMap.put(Color.BLACK, 28.0);
        colorDoubleMap.put(Color.WHITE, 28.0);

        Status status = new Status(colorDoubleMap);

        assertThat(status.getWinnerColor()).isEqualTo(Color.NONE);
    }
}
