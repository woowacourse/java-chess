package chess.model.state.finished;

import static chess.model.Team.BLACK;
import static chess.model.Team.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.model.board.Board;
import chess.model.position.Position;
import chess.model.state.Ready;
import chess.model.state.State;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StatusTest {

    @DisplayName("화이트가 폰으로 블랙팀의 폰을 잡으면 각각 37점, 37점을 반환한다.")
    @Test
    void calculateScore() {
        State state = new Ready();
        state = state.proceed(List.of("start"));
        state = state.proceed(List.of("move", "b2", "b4"));
        state = state.proceed(List.of("move", "c7", "c5"));
        state = state.proceed(List.of("move", "b4", "c5"));
        state = state.proceed(List.of("status"));

        double blackScore = state.getScore().get(BLACK);
        double whiteScore = state.getScore().get(WHITE);
        assertThat(blackScore * 100 / 100.0).isEqualTo(37.0);
        assertThat(whiteScore * 100 / 100.0).isEqualTo(37.0);
    }

    @DisplayName("화이트가 나이트로 블랙팀의 룩을 잡으면 각각 38점, 33점을 반환한다.")
    @Test
    void calculateScore_2() {
        State state = new Ready();
        state = state.proceed(List.of("start"));
        state = state.proceed(List.of("move", "b1", "c3"));
        state = state.proceed(List.of("move", "h7", "h6"));
        state = state.proceed(List.of("move", "c3", "d5"));
        state = state.proceed(List.of("move", "h6", "h5"));
        state = state.proceed(List.of("move", "d5", "b6"));
        state = state.proceed(List.of("move", "h5", "h4"));
        state = state.proceed(List.of("move", "b6", "a8"));
        state = state.proceed(List.of("status"));

        double blackScore = state.getScore().get(BLACK);
        double whiteScore = state.getScore().get(WHITE);
        assertThat(blackScore * 100 / 100.0).isEqualTo(33.0);
        assertThat(whiteScore * 100 / 100.0).isEqualTo(38.0);
    }
}
