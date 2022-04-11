package chess.service;

import static org.assertj.core.api.Assertions.assertThat;

import chess.dao.FakeSquareDao;
import chess.dao.FakeStateDao;
import chess.dto.BoardDto;
import chess.dto.MoveDto;
import chess.model.position.Position;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameServiceTest {

    @DisplayName("저장된 데이터를 초기화한다.")
    @Test
    void start() {
        GameService gameService = new GameService(new FakeSquareDao(), new FakeStateDao());
        BoardDto boardDto = gameService.start();

        for (String position : boardDto.getValues().keySet()) {
            assertThat(Position.from(position)).isInstanceOf(Position.class);
        }
    }

    @DisplayName("저장된 데이터를 삭제하기위해 보드 정보를 가지고온다.")
    @Test
    void end() {
        GameService gameService = new GameService(new FakeSquareDao(), new FakeStateDao());
        gameService.start();
        BoardDto boardDto = gameService.end();
        Map<String, String> squares = boardDto.getValues();

        for (String position : squares.keySet()) {
            assertThat(squares.get(position)).isNotNull();
        }
    }

    @DisplayName("저장된 데이터를 업데이트 힌다.")
    @Test
    void move() {
        GameService gameService = new GameService(new FakeSquareDao(), new FakeStateDao());
        gameService.start();
        BoardDto boardDto = gameService.move(new MoveDto("b2", "b4"));

        assertThat(boardDto.getValues().get("b2")).isEqualTo("NONE_NONE");
        assertThat(boardDto.getValues().get("b4")).isEqualTo("WHITE_PAWN");
    }

    @DisplayName("저장된 데이터를 불러온다.")
    @Test
    void load() {
        GameService gameService = new GameService(new FakeSquareDao(), new FakeStateDao());
        gameService.start();
        gameService.move(new MoveDto("b2", "b4"));
        BoardDto boardDto = gameService.load();

        assertThat(boardDto.getValues().get("b4")).isEqualTo("WHITE_PAWN");
    }
}
