package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.BoardFixtures;
import chess.domain.ChessBoard;
import chess.domain.generator.InitBoardGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackTurnTest {

    @DisplayName("BlackTurn에서 move를 실행하면 WhiteTurn 상태로 변경된다.")
    @Test
    void BlackTurn에서_move실행_WhiteTurn으로_상태가_변경된다() {
        State state = new BlackTurn(new ChessBoard(new InitBoardGenerator()));

        state = state.move("a7", "a6");

        assertThat(state).isInstanceOf(WhiteTurn.class);
    }

    @DisplayName("BlackTurn에서 move를 실행했을때 상대방의 King을 잡았다면 BlackWin 상태로 변경된다.")
    @Test
    void BlackTurn에서_move실행_King을_잡은경우_BlackWin으로_상태가_변경된다() {
        State state = new BlackTurn(BoardFixtures.generateWhiteKingChessBoard());

        state = state.move("a7", "a8");

        assertThat(state).isInstanceOf(BlackWin.class);
    }
}
