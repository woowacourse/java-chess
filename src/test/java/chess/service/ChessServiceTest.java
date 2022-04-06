package chess.service;

import static org.assertj.core.api.Assertions.assertThat;

import chess.controller.dto.response.ChessGameResponse;
import chess.dao.FakeGameDao;
import chess.dao.FakePieceDao;
import chess.domain.GameState;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessServiceTest {

    private final ChessService chessService = new ChessService(new FakeGameDao(), new FakePieceDao());

    @DisplayName("게임 생성 테스트")
    @Test
    void create_Game() {
        chessService.createOrLoadGame(1);
    }

    @DisplayName("게임을 불러오면 저장된 상태 그대로 나온다.")
    @Test
    void load_Game() {
        chessService.createOrLoadGame(1);

        ChessGameResponse chessGameResponse = chessService.loadGame(1);

        assertThat(chessGameResponse.getGameState()).isEqualTo(GameState.READY);
    }

    @DisplayName("재시작 요청이 들어오면 게임을 새로 생성한다.")
    @Test
    void restart_Game() {
        chessService.createOrLoadGame(1);

        ChessGameResponse chessGameResponse = chessService.restartGame(1);

        assertThat(chessGameResponse.getGameState()).isEqualTo(GameState.READY);
    }
}
