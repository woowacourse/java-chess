package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.BoardFixtures;
import chess.domain.ChessBoard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FinishedTest {

    private static final ChessBoard chessBoard = BoardFixtures.generateEmptyChessBoard();

    @DisplayName("Finished에서 start를 실행하면 게임을 재시작하여 whiteTurn으로 상태가 변경된다.")
    @Test
    void Finished에서_start실행_게임을_재시작한다() {
        State state = new WhiteWin(chessBoard);

        state = state.start();

        assertThat(state).isInstanceOf(WhiteTurn.class);
    }

    @DisplayName("Finished에서 end를 실행하면 예외가 발생한다.")
    @Test
    void Finished에서_end실행_예외가_발생한다() {
        State state = new WhiteWin(chessBoard);

        assertThatThrownBy(state::end).isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("Finished에서 move를 실행하면 예외가 발생한다.")
    @Test
    void Finished에서_move실행_예외가_발생한다() {
        State state = new WhiteWin(chessBoard);

        assertThatThrownBy(() -> state.move("a1", "a2")).isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("Running에서 isFinished를 호출하면 true가 반환된다.")
    @Test
    void Running에서_isFinished호출_true가_반환된다() {
        State state = new WhiteWin(chessBoard);

        assertThat(state.isFinished()).isTrue();
    }
}
