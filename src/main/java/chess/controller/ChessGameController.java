package chess.controller;

import chess.domain.chessGame.ChessGameState;
import chess.domain.chessGame.ReadyChessGameState;
import chess.domain.command.CommandExecutorMapper;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class ChessGameController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = OutputView.getInstance();

    public void run() {
        ChessGameState chessGameState = new ReadyChessGameState();
        playChessGame(chessGameState);
    }

    private void playChessGame(ChessGameState chessGameState) {
        outputView.printStartMessage();
        do {
            List<String> inputCommand = inputView.inputCommand();
            chessGameState = executeCommand(chessGameState, inputCommand);
        } while (chessGameState.isEnd() == false);
    }

    private ChessGameState executeCommand(ChessGameState chessGameState, List<String> inputCommand) {
        CommandExecutorMapper executorMapper = new CommandExecutorMapper(inputCommand);
        chessGameState = executorMapper.executeMapped(chessGameState);
        return chessGameState;
    }
}
