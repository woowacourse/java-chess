package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameStatusTest {

    @Test
    @DisplayName("준비 상태인지 확인한다.")
    void isReady() {
        // given
        final GameStatus gameStatus = GameStatus.READY;

        // when
        final boolean actual = gameStatus.isReady();

        // then
        assertThat(actual).isEqualTo(true);
    }

    @Test
    @DisplayName("게임이 진행중인지 확인한다.")
    void isPlaying() {
        // given
        final GameStatus gameStatus = GameStatus.PLAYING;

        // when
        final boolean actual = gameStatus.isPlaying();

        // then
        assertThat(actual).isEqualTo(true);
    }

    @Test
    @DisplayName("게임이 끝난 상태인지 확인한다.")
    void isEnd() {
        // given
        final GameStatus gameStatus = GameStatus.END;

        // when
        final boolean actual = gameStatus.isEnd();

        // then
        assertThat(actual).isEqualTo(true);
    }
}