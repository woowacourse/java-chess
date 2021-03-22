package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.board.InitBoardGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class WhiteTurnTest {

    @Test
    @DisplayName("화이트 턴 상태에서 start 명령시 에러 반환")
    void blackTurnStartException() {
        WhiteTurn whiteTurn = new WhiteTurn(new Board(InitBoardGenerator.initLines()));
        assertThatThrownBy(whiteTurn::start).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("화이트 턴 상태에서 move 명령시 블랙 턴 반환")
    void blackTurnMoveReturnWhiteTurn() {
        WhiteTurn whiteTurn = new WhiteTurn(new Board(InitBoardGenerator.initLines()));
        assertThat(whiteTurn.passTurn()).isInstanceOf(BlackTurn.class);
    }

    @Test
    @DisplayName("화이트 턴 상태에서 end 명령시 게임종료 상태 반환")
    void blackTurnEndReturnEndTurn() {
        WhiteTurn whiteTurn = new WhiteTurn(new Board(InitBoardGenerator.initLines()));
        assertThat(whiteTurn.end()).isInstanceOf(End.class);
    }
}
