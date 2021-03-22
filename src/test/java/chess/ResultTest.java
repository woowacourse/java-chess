package chess;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.game.Result;
import chess.domain.piece.Color;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResultTest {

    @Test
    @DisplayName("승패 결과 확인, WHITE 승리")
    void resultWhite() {
        Result result = new Result(10, 15);
        Map<Color, String> winOrLose = result.getWinOrLose();

        assertThat(winOrLose.get(Color.BLACK)).isEqualTo(Result.LOSE);
        assertThat(winOrLose.get(Color.WHITE)).isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("승패 결과 확인, 무승부")
    void resultDraw() {
        Result result = new Result(15, 15);
        Map<Color, String> winOrLose = result.getWinOrLose();

        assertThat(winOrLose.get(Color.BLACK)).isEqualTo(Result.DRAW);
        assertThat(winOrLose.get(Color.WHITE)).isEqualTo(Result.DRAW);
    }

    @Test
    @DisplayName("승패 결과 확인, BLACK 승리")
    void resultBlack() {
        Result result = new Result(15, 10);
        Map<Color, String> winOrLose = result.getWinOrLose();

        assertThat(winOrLose.get(Color.BLACK)).isEqualTo(Result.WIN);
        assertThat(winOrLose.get(Color.WHITE)).isEqualTo(Result.LOSE);
    }
}
