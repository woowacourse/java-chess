package chess.controller;

import chess.domain.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessController {
    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printGameGuide();
        ChessGame chessGame = createChessGame();
        outputView.printChessBoard(new ChessBoardDto(chessGame.getChessBoard()));
        while (chessGame.isRunning()) {
            executeTurn(chessGame);
        }
    }

    private ChessGame createChessGame() {
        try {
            String command = inputView.readStart();
            return ChessGame.startNewGame(command);
        }
        catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return createChessGame();
        }
    }

    private void executeTurn(final ChessGame chessGame) {
        try {
            List<String> commands = inputView.readCommands();
            chessGame.executeCommands(commands);
            outputView.printChessBoard(new ChessBoardDto(chessGame.getChessBoard()));
        }
        catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            executeTurn(chessGame);
        }
    }

}
