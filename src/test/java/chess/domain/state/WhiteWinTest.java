package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.ChessBoard;
import chess.domain.Result;
import chess.domain.generator.InitBoardGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WhiteWinTest {

    private static final ChessBoard chessBoard = new ChessBoard(new InitBoardGenerator());

    @DisplayName("WhiteWin에서 winnner를 호출하면 Rsult의 WHITE을 반환한다.")
    @Test
    void WhiteWin에서_winner호출_WHITE을_반환한다() {
        State state = new WhiteWin(chessBoard);

        assertThat(state.winner()).isEqualTo(Result.WHITE);
    }
}
