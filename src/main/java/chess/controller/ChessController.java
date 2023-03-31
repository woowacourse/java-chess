package chess.controller;

import chess.domain.chessgame.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class ChessController {
    private static final int ORDER_COMMAND = 0;

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printStartMessage();
        ChessGame chessGame = ChessGame.createInit();
        while (chessGame.isContinue()) {
            chessGame = playGame(chessGame);
        }
    }

    private ChessGame playGame(final ChessGame chessGame) {
        try {
            List<String> inputCommands = inputView.readCommand();
            String inputCommand = inputCommands.get(ORDER_COMMAND);
            Command command = CommandMapper.findCommand(inputCommand);
            return command.execute(chessGame, inputCommands, outputView);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return playGame(chessGame);
        }
    }
}
