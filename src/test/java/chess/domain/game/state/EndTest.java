package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.board.InitBoardGenerator;
import chess.domain.board.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class EndTest {
    private End end;

    @BeforeEach
    void setUp() {
        this.end = new End(new Board(InitBoardGenerator.initLines()));
    }

    @Test
    @DisplayName("종료 상태에서 start 명령시 에러 반환")
    void endStartException() {
        assertThatThrownBy(end::start).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("종료 상태에서 move 명령시 에러 반환")
    void endMoveReturnWhiteTurn() {
        assertThatThrownBy(() -> end.moveIfValidColor(Position.of("c5"), Position.of("c6")))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("종료 상태에서 end 명령시 에러 반환")
    void endEndReturnEndTurn() {
        assertThatThrownBy(end::end).isInstanceOf(IllegalStateException.class);
    }

}
