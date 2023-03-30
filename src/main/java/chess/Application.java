package chess;

import chess.controller.GameConnectionController;
import chess.domain.userAccess.UserAccessService;
import chess.domain.userAccess.room.RoomDao;
import chess.domain.userAccess.user.UserDao;
import chess.view.GameConnectionInputView;
import chess.view.GameConnectionOutputView;

public class Application {

    public static void main(String[] args) {
        GameConnectionController gameConnectionController = new GameConnectionController(
                new GameConnectionInputView(),
                new GameConnectionOutputView(),
                new UserAccessService(new UserDao(), new RoomDao())
        );
        gameConnectionController.run();
    }
}
