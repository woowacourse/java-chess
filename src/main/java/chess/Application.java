package chess;

import chess.domain.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        outputView.printGameStartMessage();

        String startOrEndInput = inputView.getStartOrEndInput(outputView);
        if (startOrEndInput.equals("start")) {
            startGame(outputView);
        }
        inputView.terminate();
    }

    private static void startGame(OutputView outputView) {
        ChessGame game = new ChessGame();
        outputView.printBoard(game.getBoard());
        
    }
}
