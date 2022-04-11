package chess.config;

import chess.controller.WebChessController;

public class ControllerConfig {

    public static WebChessController getWebController() {
        return new WebChessController(ServiceConfig.getGameService());
    }
}
