package chess.domain.game;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.dto.GameRequest;
import chess.dto.GameResponse;

class GameTest {

    @Test
    @DisplayName("게임을 생성한다.")
    public void createGame() {
        // given & when
        Game game = new Game();
        // then
        assertThat(game).isNotNull();
    }

    @Test
    @DisplayName("게임이 진행가능한지 확인한다.")
    public void testIfGameRunnable() {
        // given
        Game game = new Game();
        // when
        boolean isRunnable = game.isRunnable();
        // then
        assertThat(isRunnable).isTrue();
    }

    @Test
    @DisplayName("요청사항으로 게임을 실행한다.")
    public void runGameWithRequest() {
        // given
        Game game = new Game();

        // when
        GameRequest gameRequest = GameRequest.of("start");

        // then
        assertThatCode(() -> game.run(gameRequest))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("게임으로부터 응답을 얻는다.")
    public void getResponse() {
        // given
        Game game = new Game();
        GameRequest gameRequest = GameRequest.of("start");
        game.run(gameRequest);
        // when
        GameResponse gameResponse = game.getResponse();
        // then
        assertThat(gameResponse).isNotNull();
    }
}