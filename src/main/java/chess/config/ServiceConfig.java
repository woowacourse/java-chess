package chess.config;

import chess.service.GameService;

public class ServiceConfig {
    public static GameService getGameService() {
        return new GameService(DaoConfig.getGameDao(), DaoConfig.getBoardDao());
    }
}
