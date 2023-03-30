package chessgame.service;

import chessgame.domain.Board;
import chessgame.domain.ChessBoardFactory;
import chessgame.domain.Game;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessServiceTest {
    private final ChessService chessService = new ChessService();
    private static final String gameName = "test";
    private Game game;

    @BeforeEach
    void beforeEach() {
        game = new Game(new Board(ChessBoardFactory.create()), gameName);
        chessService.removeGame(game);
    }

    @Test
    @DisplayName("시작할 때 game이 존재하는지 테스트 한다.")
    void Should_CheckGame_WhenStart() {
        Assertions.assertThat(chessService.hasGame(gameName)).isFalse();
    }

    @Test
    @DisplayName("시작 시 이어하기 게임을 설정하도록 테스트 한다.")
    void Should_SetGame_WhenStart() {
        chessService.saveGame(game);
        Assertions.assertThat(chessService.setGame(gameName, "y")).usingRecursiveComparison().isEqualTo(game);
    }

    @Test
    @DisplayName("게임을 저장 할 때 잘 작동하는지 확인하는 테스트 한다.")
    void Should_SaveGame_WhenSaveGame() {
        Assertions.assertThatNoException().isThrownBy(() -> chessService.saveGame(game));
    }

    @Test
    @DisplayName("게임을 저장할때 저장을 위한 삭제가 잘 이루어지는지 테스트 한다.")
    void Should_RemoveGame_WhenSaveGame() {
        chessService.saveGame(game);
        Assertions.assertThatNoException().isThrownBy(() -> chessService.removeGame(game));
    }
}

