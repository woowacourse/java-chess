package chess.controller;

import chess.domain.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessController {

    private static final int COMMAND_INDEX = 0;
    private static final int SOURCE_POSITION_INDEX = 1;
    private static final int DEST_POSITION_INDEX = 2;

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        ChessGame game = ChessGame.createGame();
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
        game.startGame();
    }

    private void enterLoad(ChessGame game) {
        game.enterLoad();
    }

    private void loadGame(ChessGame game) {
        game.loadGame();
    }

    private void cancelLoad(ChessGame game) {
        game.cancelLoad();
    }

    private void movePiece(ChessGame game, List<String> commands) {
        String source = commands.get(SOURCE_POSITION_INDEX);
        String destination = commands.get(DEST_POSITION_INDEX);
        game.executeMove(source, destination);
        // TODO: 2023-03-24 DB 접근
        game.checkGameNotFinished();
    }

    private void displayGameStatus(ChessGame game) {
        game.displayGameStatus(() -> {
            outputView.printGameStatus(game.makeScoreBoard(), game.judgeWinner());
        });
    }

    private void finishGame(ChessGame game) {
        game.finishGame();
    }

    private void gameLoop(Map<Command, ChessAction> actionMap, ChessGame game) {
        try {
            List<String> commands = inputView.readCommands();
            Command command = extractCommand(commands);
            actionMap.getOrDefault(command, ChessAction.INVALID_ACTION).execute(commands);
            outputView.printChessBoard(new ChessBoardDto(game.getChessBoard()));
        } catch (IllegalArgumentException e) {
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
