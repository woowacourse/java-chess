package chess.domain.game;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ChessGameTest {
    @Test
    void 초기화_안되었는지_확인() {
        ChessGame chessGame = new ChessGame();
        assertAll(
                () -> assertThat(chessGame.isChessBoardNotInitialized()).isTrue(),
                chessGame::initChessGame,
                () -> assertThat(chessGame.isChessBoardNotInitialized()).isFalse()
        );
    }
}