package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.Board;
import chess.domain.File;
import chess.domain.Location;
import chess.domain.Rank;
import chess.domain.piece.Pawn;
import chess.domain.piece.Team;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RunningTest {

    @Test
    @DisplayName("white상태에서 start를 하면 예외가 발생한다.")
    void whiteStartTest() {
        State state = new White(new Board());
        assertThatThrownBy(state::start).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("white상태에서 end를 하면 예외가 발생한다.")
    void WhiteEndTest() {
        State state = new White(new Board());
        assertThat(state.end()).isInstanceOf(End.class);
    }

    @Test
    @DisplayName("White상태에서 getBoard를 실행하면 보드를 가져온다.")
    void WhiteGetBoardTest() {
        State state = new White(new Board());
        assertThat(state.getBoard()).isInstanceOf(Board.class);
    }

    @Test
    @DisplayName("White 상태에서 isRunning을 실행하면 true가 반환된다.")
    void WhiteIsRunningTest() {
        State state = new White(new Board());
        assertThat(state.isRunning()).isTrue();
    }

    @Test
    @DisplayName("White 상태에서 White가 아닌 기물을 움직이면 예외발생.")
    void WhiteMoveTest() {
        State state = new White(new Board());
        assertThatThrownBy(() -> state.move(Location.of(File.F, Rank.SEVEN), Location.of(File.F, Rank.FIVE)))
                .isInstanceOf(IllegalArgumentException.class);
    }


}