package chess.service;

import chess.domain.game.WebChessGame;

public class ChessGameService {

    private WebChessGame webChessGame = new WebChessGame();

    public ChessGameService() {
    }

    public void initializeGame() {
        webChessGame = new WebChessGame();
    }
}
