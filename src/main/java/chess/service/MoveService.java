package chess.service;

import chess.controller.web.ChessBoard;
import chess.dao.CommandDao;
import chess.domain.game.Command;
import chess.domain.game.Game;
import chess.domain.location.Position;

public class MoveService {
    private final CommandDao commandDao;

    public MoveService(CommandDao commandDao) {
        this.commandDao = commandDao;
    }

    public Object move(Game game, String command) {
        try {
            String[] commands = command.split(Command.SPACE_REGEX);
            String from = commands[1];
            String to = commands[2];

            game.move(Position.from(from), Position.from(to));
        } catch (IllegalArgumentException e) {
            return new ChessBoard(game).html(e.getMessage());
        }

        commandDao.insert(command);
        return new ChessBoard(game).html();
    }

}
