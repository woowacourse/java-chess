package chess.controller;

import chess.controller.command.Command;
import chess.controller.command.CommandExecute;
import chess.domain.chessGame.ChessGame;
import chess.repository.ChessGameDao;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class ChessGameController {
    public static final int COMMAND_HEAD_INDEX = 0;
    public static final int CURRENT_POSITION_INDEX = 1;
    public static final int NEXT_POSITION_INDEX = 2;

    private final InputView inputView = new InputView();
    private final OutputView outputView = OutputView.getInstance();

    private final ChessGameDao chessGameDao;

    public ChessGameController(ChessGameDao chessGameDao) {
        this.chessGameDao = chessGameDao;
    }

    public void run() {
        ChessGame chessGame = chessGameDao.findChessGame();
        if (chessGame.isReady()) {
            outputView.printStartMessage();
        }
        playChessGame(chessGame);
    }

    private void playChessGame(ChessGame chessGame) {
        do {
            printPlayingMessage(chessGame);
            chessGame = executeCommand(chessGame, inputView.inputCommand());
            chessGameDao.updateChessGame(chessGame);
        } while (!chessGame.isEnd());
        outputView.printBoard(chessGame.getPrintingBoard());
        outputView.printEndGameMessage(chessGame.calculateScore());
        chessGameDao.deleteAll();
    }

    private void printPlayingMessage(ChessGame chessGame) {
        if (!chessGame.isReady() && !chessGame.isEnd()) {
            outputView.printBoard(chessGame.getPrintingBoard());
            outputView.printTurnMessage(chessGame.getThisTurn());
        }
    }

    private ChessGame executeCommand(ChessGame chessGame, List<String> inputCommand) {
        Command command = Command.findCommand(inputCommand.get(COMMAND_HEAD_INDEX));
        CommandExecute commandExecute = command.generateExecutor(chessGame);
        return commandExecute.execute(inputCommand.get(CURRENT_POSITION_INDEX), inputCommand.get(NEXT_POSITION_INDEX));
    }
}
