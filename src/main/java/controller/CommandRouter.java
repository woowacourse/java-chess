package controller;

import connection.ChessConnectionGenerator;
import controller.command.*;
import controller.status.ChessProgramStatus;
import service.ChessBoardService;
import service.ChessGameService;
import service.ChessResultService;
import service.PlayerService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CommandRouter {

    private static final int COMMAND_KEY_INDEX = 0;

    private final Map<String, Command> startingRouter;
    private final Map<String, Command> runningRouter;

    public CommandRouter() {
        final Connection connection = ChessConnectionGenerator.getConnection();
        final PlayerService playerService = new PlayerService(connection);
        final ChessGameService chessGameService = new ChessGameService(connection);
        final ChessBoardService chessBoardService = new ChessBoardService(connection);
        final ChessResultService chessResultService = new ChessResultService(connection);

        final QuitCommand quitCommand = new QuitCommand();

        this.startingRouter = Map.of(
                "start", new StartCommand(playerService, chessGameService, chessBoardService),
                "continue", new ContinueCommand(chessGameService, chessBoardService),
                "record", new RecordCommand(playerService, chessResultService),
                "quit", quitCommand);

        this.runningRouter = Map.of(
                "move", new MoveCommand(chessBoardService, chessGameService, chessResultService),
                "status", new StatusCommand(chessResultService, chessGameService),
                "end", new EndCommand(chessGameService, chessResultService),
                "quit", quitCommand);
    }

    public ChessProgramStatus execute(final String command, final ChessProgramStatus status) throws SQLException {
        final List<String> commands = Arrays.asList(command.split(" "));
        final String commandKey = commands.get(COMMAND_KEY_INDEX);

        validateCommand(commandKey, status);

        if (status.isStarting()) {
            return startingRouter.get(commandKey).executeStart();
        }

        return runningRouter.get(commandKey).executePlay(commands, status.gameId());
    }

    private void validateCommand(final String commandKey, final ChessProgramStatus status) {
        if (status.isStarting() && !startingRouter.containsKey(commandKey)) {
            throw new IllegalArgumentException("잘못된 커맨드입니다.");
        }
        if (status.isRunning() && !runningRouter.containsKey(commandKey)) {
            throw new IllegalArgumentException("잘못된 커맨드입니다.");
        }
        if (!status.isNotEnd()) {
            throw new IllegalStateException("프로그램이 종료 상태입니다.");
        }
    }
}
