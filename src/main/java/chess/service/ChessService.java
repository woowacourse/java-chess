package chess.service;

import chess.controller.web.ChessBoard;
import chess.dao.CommandDao;
import chess.domain.game.BoardFactory;
import chess.domain.game.Command;
import chess.domain.game.Game;
import chess.domain.location.Position;

import java.util.List;

public class ChessService {
    private final CommandDao commandDao;
    private Game game;

    public ChessService(CommandDao commandDao) {
        this.commandDao = commandDao;
    }

    public Object start() {
        init();
        commandDao.deleteAll();
        return new ChessBoard(game).html();
    }

    public Object move(String command) {
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


    public Object load() {
        init();
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

    public void init() {
        game = new Game(BoardFactory.create());
    }
}