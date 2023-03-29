package chess.controller;

import chess.database.dao.ChessGameDao;
import chess.domain.ChessGame;
import chess.domain.piece.info.Team;
import chess.view.InputView;
import chess.view.OutputView;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessController {

    private static final int COMMAND_INDEX = 0;
    private static final int SOURCE_POSITION_INDEX = 1;
    private static final int DEST_POSITION_INDEX = 2;

    private final InputView inputView;
    private final OutputView outputView;
    private final ChessGameDao chessGameDao;
    private final Connection connection;

    public ChessController(final InputView inputView, final OutputView outputView,
        final ChessGameDao chessGameDao, final Connection connection) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.chessGameDao = chessGameDao;
        this.connection = connection;
    }

    public void run() {
        ChessGame game = ChessGame.createGame();
        outputView.printReadyMessage();
        outputView.printGameGuide();
        Map<Command, ChessReadyAction> createActionBoard = cretateReadyCommandActionBoard(game);
        Map<Command, ChessRunningAction> updateActionBoard = cretateCommandActionBoard(game);

        int gameId = loadGameId(createActionBoard, game);
        while (!game.isFinished()) {
            gameLoop(updateActionBoard, game, gameId);
        }
    }

    private Map<Command, ChessReadyAction> cretateReadyCommandActionBoard(ChessGame game) {
        Map<Command, ChessReadyAction> actionMap = new HashMap<>();
        actionMap.put(Command.START, new ChessReadyAction((ignore) -> startGame(game)));
        actionMap.put(Command.LOAD, new ChessReadyAction((gameId) -> loadGame(game, gameId)));
        return actionMap;
    }

    private Map<Command, ChessRunningAction> cretateCommandActionBoard(ChessGame game) {
        Map<Command, ChessRunningAction> actionMap = new HashMap<>();
        actionMap.put(Command.MOVE,
            new ChessRunningAction((commands, gameId) -> movePiece(game, commands, gameId)));
        actionMap.put(Command.STATUS,
            new ChessRunningAction((ignore1, ignore2) -> displayGameStatus(game)));
        actionMap.put(Command.END, new ChessRunningAction((ignore1, ignore2) -> finishGame(game)));
        return actionMap;
    }

    private int startGame(ChessGame game) {
        game.startGame(() -> {
            chessGameDao.insertGame(connection);
        });
        return chessGameDao.selectLastInsertedID(connection);
    }

    private int loadGame(ChessGame game, int gameId) {
        if (gameId < 0) {
            throw new IllegalArgumentException("이전에 하던 게임 기록이 없습니다.");
        }
        game.loadGame(() -> chessGameDao.findAllHistoryById(gameId, connection));
        return gameId;
    }

    private void movePiece(ChessGame game, List<String> commands, int gameId) {
        String source = commands.get(SOURCE_POSITION_INDEX);
        String destination = commands.get(DEST_POSITION_INDEX);
        game.executeMove(source, destination);
        chessGameDao.insertMoveHistory(gameId, source, destination, connection);
        checkGameNotFinished(game, gameId);
    }

    private void checkGameNotFinished(ChessGame game, int gameId) {
        Team winner = game.findWinner();
        if (winner.isRealTeam()) {
            outputView.printWinner(winner);
            chessGameDao.updateStateToFinished(gameId, winner, connection);
            finishGame(game);
        }
    }

    private void displayGameStatus(ChessGame game) {
        game.displayGameStatus(() -> {
            outputView.printGameStatus(game.makeScoreBoard(), game.judgeWinner());
        });
    }

    private void finishGame(ChessGame game) {
        try {
            game.finishGame();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int loadGameId(Map<Command, ChessReadyAction> createActionBoard, ChessGame game) {
        try {
            int gameId = chessGameDao.findRunningGameId(connection);
            List<String> commands = inputView.readCommands();
            Command command = extractCommand(commands);
            gameId = createActionBoard.getOrDefault(command, ChessReadyAction.INVALID_ACTION)
                .execute(gameId);
            outputView.printChessBoard(new ChessBoardDto(game.getChessBoard()));
            return gameId;
        } catch (Exception e) {
            outputView.printError(e.getMessage());
            return loadGameId(createActionBoard, game);
        }
    }

    private void gameLoop(Map<Command, ChessRunningAction> actionMap, ChessGame game, int gameId) {
        try {
            outputView.printTurn(game.findTeamByTurn());
            List<String> commands = inputView.readCommands();
            Command command = extractCommand(commands);
            actionMap.getOrDefault(command, ChessRunningAction.INVALID_ACTION)
                .execute(commands, gameId);
            outputView.printChessBoard(new ChessBoardDto(game.getChessBoard()));

        } catch (Exception e) {
            outputView.printError(e.getMessage());
            gameLoop(actionMap, game, gameId);
        }
    }

    private Command extractCommand(List<String> commands) {
        Command command = Command.from(commands.get(COMMAND_INDEX));
        command.validateCommandSize(commands.size());
        return command;
    }
}
