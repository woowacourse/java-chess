package chess.domain.state;

import static org.junit.jupiter.api.Assertions.*;

import chess.domain.board.Board;
import chess.domain.board.Location;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EndTest {

    @Test
    @DisplayName("end 상태일 때 start를 실행하면 예외가 발생한다.")
    void startToWhite() {
        State state = new End(new Board());
        State result = state.start();
        Assertions.assertThat(result).isInstanceOf(White.class);
    }

    @Test
    @DisplayName("end 상태일 때 start를 실행하면 보드가 새롭게 생성된다.")
    void startCheckBoard() {
        State state = new End(new Board());
        State result = state.start();
        Board board = result.getBoard();

        Board initialBoard = new Board();
        initialBoard.initializeBoard();

        Assertions.assertThat(board.getBoard()).isEqualTo(initialBoard.getBoard());
    }

    @Test
    @DisplayName("end상태에서 end를 실행하면 예외가 발생한다.")
    void end() {
        State state = new End(new Board());
        Assertions.assertThatThrownBy(state::end).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("end상태에서 isRunning을 실행하면 false를 반환한다")
    void isRunning() {
        End end = new End(new Board());
        Assertions.assertThat(end.isRunning()).isFalse();
    }

    @Test
    @DisplayName("end상태에서 move를 실행하면 예외가 발생한다.")
    void move() {
        End end = new End(new Board());
        Assertions.assertThatThrownBy(() -> end.move(Team.WHITE, Location.of("a7"), Location.of("a5")))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("end상태에서 getScore를 실행하면 예외가 발생한다.")
    void getScore() {
        End end = new End(new Board());
        Assertions.assertThatThrownBy(end::getScore).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("end상태에서 getTeam을 실행하면 예외가 발생한다.")
    void getTeam() {
        End end = new End(new Board());
        Assertions.assertThatThrownBy(end::getTeam).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("end상태에서 getNextState를 실행하면 예외가 발생한다.")
    void getNextState() {
        End end = new End(new Board());
        Assertions.assertThatThrownBy(() -> end.getNextState(new Rook(Team.BLACK)))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("end상태에서 getName을 하면 클래스 이름을 반환한다.")
    void getName() {
        End end = new End(new Board());
        Assertions.assertThat(end.getName()).isEqualTo("end");
    }
}
