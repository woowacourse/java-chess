package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.ChessBoard;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReadyTest {
    @DisplayName("Ready는 command로 \"start\"를 받으면 Progress를 반환한다.")
    @Test
    void playWithCommandStart() {
        // given
        Ready ready = new Ready(new ChessBoard());

        // when
        GameState result = ready.play(List.of("start"));

        // then
        assertThat(result).isInstanceOf(Progress.class);
    }

    @DisplayName("Ready는 command로 \"move\"를 받으면 예외가 발생한다.")
    @Test
    void playWithCommandMove() {
        // given
        Ready ready = new Ready(new ChessBoard());

        // when, then
        assertThatThrownBy(() -> ready.play(List.of("move", "b1", "b2")))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("Ready는 command로 \"end\"를 받으면 예외가 발생한다.")
    @Test
    void playWithCommandEnd() {
        // given
        Ready ready = new Ready(new ChessBoard());

        // when, then
        assertThatThrownBy(() -> ready.play(List.of("end")))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("Ready는 command로 적절하지 않은 입력을 받으면 예외가 발생한다.")
    @Test
    void playWithCommandInvalidValue() {
        // given
        Ready ready = new Ready(new ChessBoard());

        // when, then
        assertThatThrownBy(() -> ready.play(List.of("ash", "ella")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Ready는 종료되지 않은 상태이다.")
    @Test
    void isEnd() {
        // given
        Ready ready = new Ready(new ChessBoard());

        // when
        boolean result = ready.isEnd();

        // then
        assertThat(result).isFalse();
    }
}
