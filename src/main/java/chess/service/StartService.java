package chess.service;

import chess.controller.web.ChessBoard;
import chess.dao.CommandDao;
import chess.domain.game.Game;

public class StartService {

    private final CommandDao commandDao;

    public StartService(CommandDao commandDao) {
        this.commandDao = commandDao;
    }

    public Object start(Game game) {
        commandDao.deleteAll();

        return new ChessBoard(game).html();
    }

}
