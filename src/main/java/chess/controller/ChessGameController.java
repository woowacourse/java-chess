package chess.controller;

import chess.ChessGame;
import chess.ReadyChessGame;
import chess.domain.Command;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class ChessGameController {
    public static final int CURRENT_POSITION_INDEX = 1;
    public static final int NEXT_POSITION_INDEX = 2;
    public static final int COMMAND_HEAD_INDEX = 0;

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        ChessGame chessGame = new ReadyChessGame();
        playChessGame(chessGame);
    }

    private void playChessGame(ChessGame chessGame) {
        outputView.printStartMessage();
        do {
            List<String> inputCommand = inputView.inputCommand();
            chessGame = executeCommand(chessGame, inputCommand);
        } while (!chessGame.isEnd());
    }

    private ChessGame executeCommand(ChessGame chessGame, List<String> inputCommand) {
        Command command = Command.parseCommand(inputCommand.get(COMMAND_HEAD_INDEX));
        if (command == Command.START) {
            chessGame = chessGame.start();
            outputView.printBoard(chessGame.getBoard());
        }
        if (command == Command.MOVE) {
            outputView.printBoard(chessGame.move(inputCommand.get(CURRENT_POSITION_INDEX), inputCommand.get(NEXT_POSITION_INDEX)));
        }
        if (command == Command.END) {
            chessGame.end();
        }
        return chessGame;
    }
}
