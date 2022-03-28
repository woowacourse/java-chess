package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.BoardFixtures;
import chess.domain.ChessBoard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RunningTest {

    private static final ChessBoard chessBoard = BoardFixtures.generateEmptyChessBoard();

    @DisplayName("Running에서 start를 실행하면 예외가 발생한다.")
    @Test
    void Running에서_start실행_예외가_발생한다() {
        State state = new WhiteTurn(chessBoard);

        assertThatThrownBy(() -> state.start()).isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("Running에서 end를 실행하면 End 상태로 변경된다.")
    @Test
    void Ready에서_end실행_End상태로_변경된다() {
        State state = new WhiteTurn(chessBoard);

        state = state.end();

        assertThat(state).isInstanceOf(End.class);
    }

    @DisplayName("Running에서 isFinished를 호출하면 false가 반환된다.")
    @Test
    void Running에서_isFinished호출_false가_반환된다() {
        State state = new WhiteTurn(chessBoard);

        assertThat(state.isFinished()).isFalse();
    }

    @DisplayName("Running에서 winner를 실행하면 예외가 발생한다.")
    @Test
    void Running에서_winner실행_예외가_발생한다() {
        State state = new WhiteTurn(chessBoard);

        assertThatThrownBy(() -> state.winner()).isInstanceOf(UnsupportedOperationException.class);
    }
}
