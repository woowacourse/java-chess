package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.BoardFixtures;
import chess.domain.ChessBoard;
import chess.domain.generator.InitBoardGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WhiteTurnTest {

    @DisplayName("WhiteTurn에서 move를 실행하면 BlackTurn 상태로 변경된다.")
    @Test
    void WhiteTurn에서_move실행_BlackTurn으로_상태가_변경된다() {
        State state = new WhiteTurn(new ChessBoard(new InitBoardGenerator()));

        state = state.move("a2", "a3");

        assertThat(state).isInstanceOf(BlackTurn.class);
    }

    @DisplayName("WhiteTurn에서 move를 실행했을때 상대방의 King을 잡았다면 WhiteWin 상태로 변경된다.")
    @Test
    void WhiteTurn에서_move실행_King을_잡은경우_WhiteWin으로_상태가_변경된다() {
        State state = new WhiteTurn(BoardFixtures.generateBlackKingChessBoard());

        state = state.move("a7", "a8");

        assertThat(state).isInstanceOf(WhiteWin.class);
    }
}
