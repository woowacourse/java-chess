package chess.service;

import chess.domain.game.WebChessGame;

public class ChessGameService {

    private WebChessGame webChessGame = new WebChessGame();

    public ChessGameService() {
    }

    public void initializeGame() {
        webChessGame = new WebChessGame();
    }

    public boolean checkMovement(final String source, final String target) {
        return webChessGame.isMovable(makeMoveCommand(source, target));
    }

    private String makeMoveCommand(final String source, final String target) {
        return String.format("move %s %s", source, target);
    }
}
