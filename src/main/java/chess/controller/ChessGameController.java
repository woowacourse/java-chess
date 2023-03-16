package chess.controller;

import chess.domain.ChessGame;
import chess.factory.BoardFactory;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessGameController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessGameController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        ChessGame chessGame = new ChessGame(BoardFactory.createBoard());

        outputView.printStartMessage();

        List<String> inputCommand = inputView.readGameCommand();

        playChess(chessGame, inputCommand);
    }

    private void playChess(ChessGame chessGame, List<String> inputCommand) {
        while (isNotEnd(inputCommand)) {
            try {
                chessGame = createNewChessGame(chessGame, inputCommand);
                tryChessMove(chessGame, inputCommand);
                outputView.printBoard(chessGame.getBoard());
                inputCommand = inputView.readGameCommand();
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
                inputCommand = inputView.readGameCommand();
            }
        }
    }

    private boolean isNotEnd(final List<String> inputCommand) {
        return !inputCommand.get(0).equals("end");
    }

    private void tryChessMove(final ChessGame chessGame, final List<String> inputCommand) {
        if (inputCommand.get(0).startsWith("move")) {
            chessGame.move(inputCommand.get(1), inputCommand.get(2));
        }
    }

    private ChessGame createNewChessGame(ChessGame chessGame, final List<String> inputCommand) {
        if (inputCommand.get(0).equals("start")) {
            chessGame = new ChessGame(BoardFactory.createBoard());
        }
        return chessGame;
    }
}
