package chess.domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.event.Event;
import chess.domain.event.InitEvent;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class NewGameTest {

    @Test
    void play_메서드에_INIT_이벤트_전달시_백색_턴을_반환() {
        Game game = new NewGame();

        Game actual = game.play(new InitEvent());

        assertThat(actual).isInstanceOf(WhiteTurn.class);
    }

    @Test
    void 아직_게임_종료_전() {
        Game game = new NewGame();

        boolean actual = game.isEnd();

        assertThat(actual).isFalse();
    }
}