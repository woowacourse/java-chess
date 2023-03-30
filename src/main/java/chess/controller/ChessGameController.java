package chess.controller;

import chess.controller.command.Command;
import chess.controller.command.CommandExecute;
import chess.domain.chessGame.ChessGameState;
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
        ChessGameState chessGameState = chessGameDao.findChessGame();
        if (chessGameState.isReady()) {
            outputView.printStartMessage();
        }
        playChessGame(chessGameState);
    }

    private void playChessGame(ChessGameState chessGameState) {
        do {
            printPlayingMessage(chessGameState);
            chessGameState = executeCommand(chessGameState, inputView.inputCommand());
            chessGameDao.updateChessGame(chessGameState);
        } while (!chessGameState.isEnd());
        outputView.printBoard(chessGameState.getPrintingBoard());
        outputView.printEndGameMessage(chessGameState.calculateScore());
        chessGameDao.deleteAll();
    }

    private void printPlayingMessage(ChessGameState chessGameState) {
        if (!chessGameState.isReady() && !chessGameState.isEnd()) {
            outputView.printBoard(chessGameState.getPrintingBoard());
            outputView.printTurnMessage(chessGameState.getThisTurn());
        }
    }

    private ChessGameState executeCommand(ChessGameState chessGameState, List<String> inputCommand) {
        Command command = Command.findCommand(inputCommand.get(COMMAND_HEAD_INDEX));
        CommandExecute commandExecute = command.generateExecutor(chessGameState);
        return commandExecute.execute(inputCommand.get(CURRENT_POSITION_INDEX), inputCommand.get(NEXT_POSITION_INDEX));
    }
}
