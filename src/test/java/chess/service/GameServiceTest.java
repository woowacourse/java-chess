package chess.service;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.game.Game;
import chess.dto.game.GamePutRequestDto;
import chess.service.testdao.TestGameDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameServiceTest {

    private GameService gameService;

    @BeforeEach
    void setup() {
        gameService = new GameService(new TestGameDao());
    }

    @Test
    void addAndFind() {
        final long id = gameService.add(1L, 2L);
        final Game game = gameService.find(id);
        assertThat(game.getWhiteId()).isEqualTo(1L);
        assertThat(game.getBlackId()).isEqualTo(2L);
    }

    @Test
    void update() {
        final long id = gameService.add(1L, 2L);
        gameService.update(new GamePutRequestDto(id, "black", true));
        final Game game = gameService.find(id);
        assertThat(game.getTurnValue()).isEqualTo("black");
        assertThat(game.isFinished()).isTrue();
    }

}
