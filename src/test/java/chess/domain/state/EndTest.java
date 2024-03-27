package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.dao.DaoTest;
import chess.dao.TestConnectionGenerator;
import chess.domain.board.ChessBoard;
import chess.domain.piece.Color;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EndTest implements DaoTest {
    private ChessBoard chessBoard;

    @BeforeEach
    void setUpChessBoard() {
        chessBoard = new ChessBoard(1, new TestConnectionGenerator());
    }

    @DisplayName("End는 command로 \"start\"를 받으면 예외가 발생한다.")
    @Test
    void playWithCommandStart() {
        // given
        End end = new End(chessBoard);

        // when, then
        assertThatThrownBy(() -> end.play(List.of("start")))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("End는 command로 \"move\"를 받으면 예외가 발생한다.")
    @Test
    void playWithCommandMove() {
        // given
        End end = new End(chessBoard);

        // when, then
        assertThatThrownBy(() -> end.play(List.of("move", "b1", "b2")))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("End는 command로 \"end\"를 받으면 예외가 발생한다.")
    @Test
    void playWithCommandEnd() {
        // given
        End end = new End(chessBoard);

        // when, then
        assertThatThrownBy(() -> end.play(List.of("end")))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("End는 command로 적절하지 않은 입력을 받으면 예외가 발생한다.")
    @Test
    void playWithCommandInvalidValue() {
        // given
        End end = new End(chessBoard);

        // when, then
        assertThatThrownBy(() -> end.play(List.of("ash", "ella")))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("End는 종료된 상태이다.")
    @Test
    void isEnd() {
        // given
        End end = new End(chessBoard);

        // when
        boolean result = end.isEnd();

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("종료된 게임은 점수를 계산할 수 없다.")
    @Test
    void calculateScore() {
        // given
        End end = new End(chessBoard);

        // when, then
        assertThatThrownBy(() -> end.calculateScore(Color.BLACK)).isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("종료된 게임은 승패를 판단할 수 없다.")
    @Test
    void getWinnerColor() {
        // given
        End end = new End(chessBoard);

        // when, then
        assertThatThrownBy(end::getWinnerColor).isInstanceOf(UnsupportedOperationException.class);
    }
}
