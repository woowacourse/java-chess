package chess.controller;

import chess.controller.command.CommandExecutorMapper;
import chess.domain.chessGame.ChessGameState;
import chess.repository.ChessGameDao;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class ChessGameController {
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
        CommandExecutorMapper executorMapper = new CommandExecutorMapper(inputCommand);
        chessGameState = executorMapper.executeMapped(chessGameState);
        return chessGameState;
    }
}
