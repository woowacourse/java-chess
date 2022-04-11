package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.dto.BoardDto;
import chess.model.position.Position;
import chess.service.GameService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameServiceTest {

    @DisplayName("저장된 데이터를 초기화한다.")
    @Test
    void start() {
        GameService gameService = new GameService(new FakeSquareDao(), new FakeTurnDao());
        BoardDto boardDto = gameService.start();

        for (String position : boardDto.getValues().keySet()) {
            assertThat(Position.from(position)).isInstanceOf(Position.class);
        }
    }

    @DisplayName("저장된 데이터를 삭제한다.")
    @Test
    void end() {
        GameService gameService = new GameService(new FakeSquareDao(), new FakeTurnDao());
        gameService.start();
        BoardDto boardDto = gameService.end();

        assertThat(boardDto.getValues().size()).isEqualTo(0);
    }

    @DisplayName("저장된 데이터를 업데이트 힌다.")
    @Test
    void move() {
        GameService gameService = new GameService(new FakeSquareDao(), new FakeTurnDao());
        gameService.start();
        BoardDto boardDto = gameService.move("b2", "b4");

        assertThat(boardDto.getValues().get("b2")).isEqualTo("NONE_NONE");
        assertThat(boardDto.getValues().get("b4")).isEqualTo("WHITE_PAWN");
    }

    @DisplayName("저장된 데이터를 불러온다.")
    @Test
    void load() {
        GameService gameService = new GameService(new FakeSquareDao(), new FakeTurnDao());
        gameService.start();
        gameService.move("b2", "b4");
        BoardDto boardDto = gameService.load();

        assertThat(boardDto.getValues().get("b4")).isEqualTo("WHITE_PAWN");
    }
}
