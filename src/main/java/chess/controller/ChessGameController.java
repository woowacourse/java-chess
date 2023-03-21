package chess.controller;

import chess.domain.Command;
import chess.domain.chessGame.ChessGame;
import chess.domain.chessGame.ReadyChessGame;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessGameController {
    public static final int COMMAND_HEAD_INDEX = 0;
    public static final int CURRENT_POSITION_INDEX = 1;
    public static final int NEXT_POSITION_INDEX = 2;

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        ChessGame chessGame = new ReadyChessGame();
        playChessGame(chessGame);
    }

    private void playChessGame(ChessGame chessGame) {
        outputView.printStartMessage();
        do {
            chessGame = executeCorrectCommand(chessGame);
        } while (chessGame.isPlaying());
    }

    private ChessGame executeCorrectCommand(ChessGame chessGame){
        ChessGame newChessGame = null;
        try {
            List<String> inputCommand = inputView.inputCommand();
            newChessGame = executeCommand(chessGame, inputCommand);
        } catch (IllegalArgumentException e){
            outputView.printErrorMessage(e.getMessage());
            return executeCorrectCommand(chessGame);
        }
        return newChessGame;
    }

    private ChessGame executeCommand(ChessGame chessGame, List<String> inputCommand) {
        Command command = Command.parseCommand(inputCommand.get(COMMAND_HEAD_INDEX));
        if (command == Command.START) {
            chessGame = chessGame.start();
            outputView.printBoard(chessGame.getPrintingBoard());
        }
        if (command == Command.MOVE) {
            chessGame = chessGame.move(inputCommand.get(CURRENT_POSITION_INDEX),
                    inputCommand.get(NEXT_POSITION_INDEX));
            outputView.printBoard(chessGame.getPrintingBoard());
        }
        if (command == Command.END) {
            chessGame = chessGame.end();
        }
        return chessGame;
    }


}
