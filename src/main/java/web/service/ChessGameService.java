package web.service;

import chess.domain.board.Board;
import chess.domain.board.InitBoardGenerator;
import chess.domain.command.Command;
import chess.domain.command.Commands;
import chess.domain.game.ChessGame;
import web.dao.ChessGameDao;

import java.sql.SQLException;
import java.util.List;

public class ChessGameService {
    private static final ChessGameDao chessGameDao = new ChessGameDao();

    public ChessGame chessGameByRoomId(int roomId) throws SQLException {
        ChessGame chessGame = new ChessGame(new Board(InitBoardGenerator.initLines()));
        chessGame.start();
        Commands commands = Commands.initCommands(chessGame);

        List<String> moves = chessGameDao.findByRoomId(roomId);

        for (String move : moves) {
            Command command = commands.matchedCommand(move);
            command.execution(move);
        }

        return chessGame;
    }

    public void addCommand(String webCommand, int roomId) throws SQLException {
        chessGameDao.addCommand(webCommand, roomId);
    }
}
