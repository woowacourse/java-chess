package chess.controller;

import chess.controller.game.GameController;
import chess.controller.main.MainCommand;
import chess.controller.main.MainController;
import chess.controller.session.RoomSession;
import chess.controller.session.UserSession;
import java.util.Map;

public class ControllerFactory {
    private static final Controller INSTANCE;

    static {
        final CommandMapper<MainCommand, Controller> mainCommandMapper = new CommandMapper<>(Map.of(
                MainCommand.START, chessGameController(),
                MainCommand.END, empty()
        ));
        INSTANCE = new MainController(UserSession.getInstance(), RoomSession.getInstance(), mainCommandMapper);
    }

    private static Controller chessGameController() {
        return new GameController();
    }

    private static Controller empty() {
        return () -> {
        };
    }

    public static Controller mainController() {
        return INSTANCE;
    }
}
