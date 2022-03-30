package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.ChessBoard;
import chess.domain.Result;
import chess.domain.generator.InitBoardGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackWinTest {

    private static final ChessBoard chessBoard = new ChessBoard(new InitBoardGenerator());

    @DisplayName("BlackWin에서 winnner를 호출하면 Rsult의 BLACK을 반환한다.")
    @Test
    void BlackWin에서_winner호출_BLACK을_반환한다() {
        State state = new BlackWin(chessBoard);

        assertThat(state.winner()).isEqualTo(Result.BLACK);
    }
}
