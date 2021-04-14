package chess.service;

import chess.controller.web.ChessBoard;
import chess.dao.CommandDao;
import chess.domain.game.Command;
import chess.domain.game.Game;
import chess.domain.location.Position;

import java.util.List;

public class LoadService {

    private final CommandDao commandDao;

    public LoadService(CommandDao commandDao) {
        this.commandDao = commandDao;
    }

    public Object load(Game game) {
        List<String> commands = commandDao.selectAll();
        for (String command : commands) {
            action(game, command);
        }

        return new ChessBoard(game).html();
    }

    private void action(Game game, String command) {
        String[] commands = command.split(Command.SPACE_REGEX);
        String from = commands[1];
        String to = commands[2];

        game.move(Position.from(from), Position.from(to));
    }

}
