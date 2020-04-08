package chess.service;

import chess.controller.command.Command;
import chess.controller.command.CommandReader;
import chess.dao.CommandLogDao;
import chess.dbconnect.JDBCConnector;
import chess.domain.gamestatus.GameStatus;
import chess.domain.gamestatus.NothingHappened;
import chess.dto.ChessGameDTO;
import java.sql.SQLException;
import java.util.List;

public class ChessGameService {

    private GameStatus gameStatus;
    private CommandLogDao commandLogDao = new CommandLogDao(new JDBCConnector());

    public ChessGameDTO loadRecord() throws SQLException {
        this.gameStatus = new NothingHappened();
        this.gameStatus = gameStatus.start();

        executePastRequests();

        return new ChessGameDTO(
            gameStatus.getBoard(), gameStatus.scoring(), gameStatus.isFinished());
    }

    private void executePastRequests() throws SQLException {
        List<String> commandLogs = commandLogDao.getAllByOldOneFirst();

        for (String commandLog : commandLogs) {
            Command command = CommandReader.of(commandLog);
            gameStatus = command.execute(gameStatus);
        }
    }

    public ChessGameDTO move(String fromPosition, String toPosition) throws SQLException {
        String requestCommand = "move " + fromPosition + " " + toPosition;

        Command command = CommandReader.of(requestCommand);
        gameStatus = command.execute(gameStatus);
        commandLogDao.add(requestCommand);

        return new ChessGameDTO(
            gameStatus.getBoard(), gameStatus.scoring(), gameStatus.isFinished());
    }
}
