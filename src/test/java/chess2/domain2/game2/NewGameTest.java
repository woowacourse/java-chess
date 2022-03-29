package chess2.domain2.game2;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class NewGameTest {

    @Test
    void init_메서드_실행시_백색_턴을_반환() {
        Game game = new NewGame();

        Game actual = game.init();

        assertThat(actual).isInstanceOf(WhiteTurn.class);
    }

    @Test
    void 아직_게임_종료_전() {
        Game game = new NewGame();

        boolean actual = game.isEnd();

        assertThat(actual).isFalse();
    }
}