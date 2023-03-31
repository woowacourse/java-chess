package chess.controller;

import chess.controller.game.ChessGameController;
import chess.controller.login.LoginSessionController;
import chess.controller.room.RoomController;

public class FrontController {

    public void init() {
        ensureLoggedIn();
        enterRoom();
        playGame();
    }

    private void ensureLoggedIn() {
        LoginSessionController loginSessionController = LoginSessionController.getInstance();
        loginSessionController.ensureLogin();
    }

    private void enterRoom() {
        RoomController.getInstance().enterRoom();
    }

    private void playGame() {
        ChessGameController gameController = ChessGameController.getInstance();
        gameController.playGame();
    }

}
