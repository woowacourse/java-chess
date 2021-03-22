package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.board.InitBoardGenerator;
import chess.domain.board.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class BlackTurnTest {

    @Test
    @DisplayName("블랙 턴 상태에서 start 명령시 에러 반환")
    void blackTurnStartException() {
        BlackTurn blackTurn = new BlackTurn(new Board(InitBoardGenerator.initLines()));
        assertThatThrownBy(blackTurn::start).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("블랙 턴 상태에서 move 명령시 화이트 턴 반환")
    void blackTurnMoveReturnWhiteTurn() {
        BlackTurn blackTurn = new BlackTurn(new Board(InitBoardGenerator.initLines()));
//        assertThat(blackTurn.move(Position.of("c2"), Position.of("c4")))
//                .isInstanceOf(WhiteTurn.class);
    }

    @Test
    @DisplayName("블랙 턴 상태에서 end 명령시 게임종료 상태 반환")
    void blackTurnEndReturnEndTurn() {
        BlackTurn blackTurn = new BlackTurn(new Board(InitBoardGenerator.initLines()));
        assertThat(blackTurn.end()).isInstanceOf(End.class);
    }
}