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
    private int gameId;

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
        Map<Command, ChessAction> actionMap = cretateCommandActionMap(game);

        while (!game.isFinished()) {
            gameLoop(actionMap, game);
        }
    }

    private Map<Command, ChessAction> cretateCommandActionMap(ChessGame game) {
        Map<Command, ChessAction> actionMap = new HashMap<>();
        actionMap.put(Command.START, new ChessAction(ignore -> startGame(game)));
        actionMap.put(Command.LOAD, new ChessAction(ignore -> enterLoad(game)));
        actionMap.put(Command.CONTINUE, new ChessAction(ignore -> loadGame(game)));
        actionMap.put(Command.CANCEL, new ChessAction(ignore -> cancelLoad(game)));
        actionMap.put(Command.MOVE, new ChessAction(commands -> movePiece(game, commands)));
        actionMap.put(Command.STATUS, new ChessAction(ignore -> displayGameStatus(game)));
        actionMap.put(Command.END, new ChessAction(ignore -> finishGame(game)));
        return actionMap;
    }

    private void startGame(ChessGame game) {
        game.startGame(() -> {
            chessGameDao.insertGame(connection);
            gameId = chessGameDao.selectLastInsertedID(connection);
        });
    }

    private void enterLoad(ChessGame game) {
        game.enterLoad(() -> {
            gameId = chessGameDao.findRunningGameId(connection);
            if (gameId < 0) {
                outputView.printCannotLoadMessage();
                cancelLoad(game);
                return;
            }
            outputView.printLoadGuide();
        });
    }

    private void loadGame(ChessGame game) {
        game.loadGame(() -> chessGameDao.findAllHistoryById(gameId, connection));
    }

    private void cancelLoad(ChessGame game) {
        game.cancelLoad(()->outputView.printGameGuide());
    }

    private void movePiece(ChessGame game, List<String> commands) {
        String source = commands.get(SOURCE_POSITION_INDEX);
        String destination = commands.get(DEST_POSITION_INDEX);
        game.executeMove(source, destination);
        chessGameDao.insertMoveHistory(gameId, source, destination, connection);
        checkGameNotFinished(game);
    }

    private void checkGameNotFinished(ChessGame game) {
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

    private void gameLoop(Map<Command, ChessAction> actionMap, ChessGame game) {
        try {
            List<String> commands = inputView.readCommands();
            Command command = extractCommand(commands);
            actionMap.getOrDefault(command, ChessAction.INVALID_ACTION).execute(commands);
            if (game.isRunningOrFinished()) {
                outputView.printChessBoard(new ChessBoardDto(game.getChessBoard()));
            }
        } catch (Exception e) {
            outputView.printError(e.getMessage());
            gameLoop(actionMap, game);
        }
    }

    private Command extractCommand(List<String> commands) {
        Command command = Command.from(commands.get(COMMAND_INDEX));
        command.validateCommandSize(commands.size());
        return command;
    }
}
