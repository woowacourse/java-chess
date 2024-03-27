package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.ChessBoard;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProgressTest {
    @DisplayName("Progress는 command로 \"start\"를 받으면 예외가 발생한다.")
    @Test
    void playWithCommandStart() {
        // given
        Progress progress = new Progress(new ChessBoard());

        // when, then
        assertThatThrownBy(() -> progress.play(List.of("start")))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("Progress는 command로 \"move\"를 받으면 Progress를 반환한다.")
    @Test
    void playWithCommandMove() {
        // given
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.initialBoard();
        Progress progress = new Progress(chessBoard);

        // when
        GameState result = progress.play(List.of("move", "b2", "b3"));

        // then
        assertThat(result).isInstanceOf(Progress.class);
    }

    @DisplayName("Progress는 command로 \"end\"를 받으면 End를 반환한다.")
    @Test
    void playWithCommandEnd() {
        // given
        Progress progress = new Progress(new ChessBoard());

        // when
        GameState result = progress.play(List.of("end"));

        // then
        assertThat(result).isInstanceOf(End.class);
    }

    @DisplayName("Progress는 command로 적절하지 않은 입력을 받으면 예외가 발생한다.")
    @Test
    void playWithCommandInvalidValue() {
        // given
        Progress progress = new Progress(new ChessBoard());

        // when, then
        assertThatThrownBy(() -> progress.play(List.of("ash", "ella")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Progress는 종료되지 않은 상태이다.")
    @Test
    void isEnd() {
        // given
        Progress progress = new Progress(new ChessBoard());

        // when
        boolean result = progress.isEnd();

        // then
        assertThat(result).isFalse();
    }
}
