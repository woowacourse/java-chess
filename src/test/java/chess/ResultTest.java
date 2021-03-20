package chess;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.game.Result;
import chess.domain.piece.Color;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class ResultTest {

    @Test
    void result() {
        Result result = new Result(10, 15);
        Map<Color, String> winOrLose = result.getWinOrLose();

        assertThat(winOrLose.get(Color.BLACK)).isEqualTo("패");
        assertThat(winOrLose.get(Color.WHITE)).isEqualTo("승");
    }

    @Test
    void resultDraw() {
        Result result = new Result(15, 15);
        Map<Color, String> winOrLose = result.getWinOrLose();

        assertThat(winOrLose.get(Color.BLACK)).isEqualTo("무");
        assertThat(winOrLose.get(Color.WHITE)).isEqualTo("무");
    }
}
