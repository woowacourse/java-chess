package chess;

import static org.junit.jupiter.api.Assertions.*;

import chess.domain.ChessGame;
import org.junit.jupiter.api.Test;

class WebChessServiceTest {

    @Test
    void initialize() {
        WebChessService webChessService = new WebChessService();
        ChessGame chessGame = new ChessGame();
        chessGame.start();
        webChessService.initializeGame(chessGame.getState());
    }
}
