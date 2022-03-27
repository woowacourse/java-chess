package chess.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class NewGameTest {

    @Test
    void init_메서드_실행시_백색_턴을_반환() {
        Game game = new NewGame();

        Game actual = game.init();

        assertThat(actual).isInstanceOf(WhiteTurn.class);
    }
}
