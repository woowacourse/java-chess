package domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SideTest {
    @DisplayName("black진영이 white진영보다 점수가 높으면 black진영이 승자가 된다.")
    @Test
    void blackWinTest() {
        //given
        double whiteScore = 15;
        double blackScore = 16;
        assertThat(Side.calculateWinner(whiteScore, blackScore)).isEqualTo(Side.BLACK);
    }

    @DisplayName("black진영이 white진영보다 점수가 낮으면 white진영이 승자가 된다.")
    @Test
    void whiteWinTest() {
        //given
        double whiteScore = 17;
        double blackScore = 16;
        assertThat(Side.calculateWinner(whiteScore, blackScore)).isEqualTo(Side.WHITE);
    }

    @DisplayName("white, black이 동점이면 무승부다")
    @Test
    void drawTest() {
        double whiteScore = 17;
        double blackScore = 17;
        assertThat(Side.calculateWinner(whiteScore, blackScore)).isEqualTo(Side.NEUTRAL);
    }

}