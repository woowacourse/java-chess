package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EndTest {
    @DisplayName("End는 command로 \"start\"를 받으면 예외가 발생한다.")
    @Test
    void playWithCommandStart() {
        // given
        End end = new End();

        // when, then
        assertThatThrownBy(() -> end.play(List.of("start")))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("End는 command로 \"move\"를 받으면 예외가 발생한다.")
    @Test
    void playWithCommandMove() {
        // given
        End end = new End();

        // when, then
        assertThatThrownBy(() -> end.play(List.of("move", "b1", "b2")))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("End는 command로 \"end\"를 받으면 예외가 발생한다.")
    @Test
    void playWithCommandEnd() {
        // given
        End end = new End();

        // when, then
        assertThatThrownBy(() -> end.play(List.of("end")))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("End는 command로 적절하지 않은 입력을 받으면 예외가 발생한다.")
    @Test
    void playWithCommandInvalidValue() {
        // given
        End end = new End();

        // when, then
        assertThatThrownBy(() -> end.play(List.of("ash", "ella")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("End는 종료된 상태이다.")
    @Test
    void isEnd() {
        // given
        End end = new End();

        // when
        boolean result = end.isEnd();

        // then
        assertThat(result).isTrue();
    }
}
