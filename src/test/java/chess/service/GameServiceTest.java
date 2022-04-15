package chess.service;

import static org.assertj.core.api.Assertions.assertThat;

import chess.dto.BoardDto;
import chess.dto.MoveDto;
import chess.model.position.Position;
import chess.repository.FakeGameRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameServiceTest {

    @DisplayName("게임을 시작한다.")
    @Test
    void start() {
        GameService gameService = new GameService(new FakeGameRepository());
        BoardDto boardDto = gameService.start();

        for (String position : boardDto.getValues().keySet()) {
            assertThat(Position.from(position)).isInstanceOf(Position.class);
        }
    }

    @DisplayName("현재 상태에서 다음 상태로 진행한다.")
    @Test
    void move() {
        GameService gameService = new GameService(new FakeGameRepository());
        gameService.start();
        BoardDto boardDto = gameService.move(new MoveDto("b2", "b4"));

        assertThat(boardDto.getValues().get("b2")).isEqualTo("NONE_NONE");
        assertThat(boardDto.getValues().get("b4")).isEqualTo("WHITE_PAWN");
    }
}
