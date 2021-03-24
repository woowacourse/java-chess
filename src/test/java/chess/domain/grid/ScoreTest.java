package chess.domain.grid;

import chess.domain.grid.gridStrategy.NormalGridStrategy;
import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.Pawn;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class ScoreTest {
    @Test
    @DisplayName("Score를 정상적으로 생성하는 지 테스트")
    public void init() {
        assertThatCode(() -> {
            Grid grid = new Grid(new NormalGridStrategy());
            new Score(grid.lines());
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("게임 시작 시에 아무 말도 안 잡혔을 때 38점인지 체크")
    public void score_WhenInit() {
        Grid grid = new Grid(new NormalGridStrategy());
        Score score = new Score(grid.lines());
        assertThat(score.score(Color.BLACK)).isEqualTo(38);
    }

    @Test
    @DisplayName("세로 줄에 Pawn이 2개 이상 겹쳐있으면 각 Pawn의 점수를 0.5점으로 계산")
    public void score_WhenPawnStraight() {
        Grid grid = new Grid(new NormalGridStrategy());
        grid.lines().assign(new Position("b3"), new Pawn(Color.BLACK, 'b', '3'));
        grid.lines().assign(new Position("b4"), new Pawn(Color.BLACK, 'b', '4'));
        Score score = new Score(grid.lines());
        assertThat(score.score(Color.BLACK)).isEqualTo(38.5);
    }

    @Test
    @DisplayName("Pawn이 1개 잡혔을 때 37점인지 테스트")
    public void score_WhenOnePawnCaught() {
        Grid grid = new Grid(new NormalGridStrategy());
        grid.lines().assign(new Position("b2"), new Empty('b', '2'));
        Score score = new Score(grid.lines());
        assertThat(score.score(Color.WHITE)).isEqualTo(37);
    }
}
