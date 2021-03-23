package chess.domain.gamestate.finished;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Board;
import chess.domain.gamestate.CommandType;
import chess.domain.gamestate.State;
import chess.utils.BoardUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EndTest {

    private Board board;
    private State state;

    @BeforeEach
    void setUp() {
        board = BoardUtil.generateInitialBoard();
        state = new End(board);
    }

    @DisplayName("상태 변경 - end 상태에선 어떤 명령어도 안된다.")
    @Test
    void changeState_1() {
        // given
        CommandType start = CommandType.START;

        // then
        assertThatThrownBy(() -> state.changeCommand(start))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
