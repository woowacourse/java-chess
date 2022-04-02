package chess.domain.game.state;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import chess.domain.boardstrategy.InitBoardStrategy;
import chess.domain.game.ChessGame;
import chess.domain.game.state.attribute.StateType;
import chess.dto.CommandDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTest {
    private final ChessGame chessGame = new ChessGame(new InitBoardStrategy());
    private State state = new Result(chessGame);

    @Test
    @DisplayName("status 커멘드를 입력할 시 StatusEnd 상태로 이동한다.")
    void execute() {
        state = state.execute(new CommandDto("status"));
        assertThat(state)
                .isInstanceOf(Status.class);
    }

    @Test
    @DisplayName("이외 커멘드 입력 시 오류를 출력한다.")
    void executeError() {
        assertThatThrownBy(() -> state.execute(new CommandDto("move a2 a3")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("status 를 입력하세요.");
    }

    @Test
    @DisplayName("isPlay() 실행 시 false 를 리턴한다")
    void isPlay() {
        assertThat(state.getType() == StateType.PLAY)
                .isFalse();
    }

    @Test
    @DisplayName("isRun() 실행 시 true 를 리턴한다")
    void isRun() {
        assertThat(state.getType() != StateType.END)
                .isTrue();
    }

    @Test
    @DisplayName("해당 상태가 Status 가 아님을 확인할 수 있다.")
    void isStatus() {
        assertThat(state.getType() == StateType.STATUS)
                .isFalse();
    }
}
