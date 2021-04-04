package chess;

import chess.domain.ChessGame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChessGameTest {
    @DisplayName("게임 생성 확인")
    @Test
    void create() {
//        assertDoesNotThrow(ChessGame::new);
    }

    @DisplayName("게임 진행 시 isNotEnd 참 확인")
    @Test
    void isPlaying() {
        ChessGame chessGame = new ChessGame();
        assertTrue(chessGame.isProgressing());
    }

    //TODO: 턴 바뀌는지 확인
}
