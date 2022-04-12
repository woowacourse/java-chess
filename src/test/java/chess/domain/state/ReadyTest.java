package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Board;
import chess.domain.board.Location;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReadyTest {

    @Test
    @DisplayName("ready상태에서 start를 하면 게임이 실행된다.")
    void start() {
        State state = new Ready();
        assertThat(state.start()).isInstanceOf(Running.class);
    }

    @Test
    @DisplayName("ready상태에서 start를 하면 보드가 초기화된다.")
    void startGetBoard() {
        State state = new Ready();
        state.start();
        Board board = state.getBoard();
        Board initialBoard = new Board();
        initialBoard.initializeBoard();
        assertThat(board.getBoard()).isEqualTo(initialBoard.getBoard());
    }

    @Test
    @DisplayName("ready상태에서 end를 하면 예외가 발생한다..")
    void end() {
        State state = new Ready();
        assertThatThrownBy(state::end).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("ready상태에서 getBoard를 실행하면 예외가 발생한다.")
    void getBoard() {
        State state = new Ready();
        assertThatThrownBy(state::getBoard).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("ready상태에서 isRunning을 실행하면 false가 반환된다.")
    void isRunning() {
        State state = new Ready();
        assertThat(state.isRunning()).isFalse();
    }

    @Test
    @DisplayName("ready상태에서 move를 실행하면 예외가 발생한다.")
    void move() {
        State state = new Ready();
        assertThatThrownBy(() -> state.move(Team.BLACK, Location.of("a1"), Location.of("a2")))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("ready상태에서 getScore를 실행하면 예외가 발생한다.")
    void getScore() {
        State state = new Ready();
        assertThatThrownBy(state::getScore).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("ready상태에서 getTeam를 실행하면 예외가 발생한다.")
    void getTeam() {
        State state = new Ready();
        assertThatThrownBy(state::getTeam).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("ready상태에서 getNextState를 실행하면 예외가 발생한다.")
    void getNextState() {
        State state = new Ready();
        assertThatThrownBy(() -> state.getNextState(new Rook(Team.WHITE))).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("ready상태에서 getName을 하면 상태 이름을 반환한다.")
    void getName() {
        State state = new Ready();
        Assertions.assertThat(state.getName()).isEqualTo("ready");
    }
}
