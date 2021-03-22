package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.board.InitBoardGenerator;
import chess.domain.board.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class InitTest {
    private Init init;

    @BeforeEach
    void setUp() {
        this.init = new Init(new Board(InitBoardGenerator.initLines()));
    }

    @Test
    @DisplayName("초기 상태에서 start 명령시 화이트 턴 반환")
    void endStartException() {
        assertThat(init.start()).isInstanceOf(WhiteTurn.class);
    }

    @Test
    @DisplayName("초기 상태에서 move 명령시 에러 반환")
    void endMoveReturnWhiteTurn() {
        assertThatThrownBy(() -> init.moveIfValidColor(Position.of("c5"), Position.of("c6")))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("초기 상태에서 end 명령시 게임 종료 상태 반환")
    void endEndReturnEndTurn() {
        assertThat(init.end()).isInstanceOf(End.class);
    }
}
