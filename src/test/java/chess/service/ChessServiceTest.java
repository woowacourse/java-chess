package chess.service;

import static org.assertj.core.api.Assertions.assertThat;

import chess.dto.GameDto;
import org.junit.jupiter.api.Test;

class ChessServiceTest {

    @Test
    void getGame() {
        ChessService chessService = new ChessService(new FakeBoardDao(), new FakeGameDao());

        GameDto game = chessService.getGame();

        assertThat(game).isInstanceOf(GameDto.class);
    }
}
