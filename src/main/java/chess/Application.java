package chess;

import chess.controller.UserAccessController;
import chess.domain.userAccess.UserAccessService;
import chess.domain.userAccess.room.RoomDao;
import chess.domain.userAccess.user.UserDao;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {

    public static void main(String[] args) {
        UserAccessController userAccessController = new UserAccessController(
                new InputView(),
                new OutputView(),
                new UserAccessService(new UserDao(), new RoomDao())
        );
        userAccessController.run();
    }
}
