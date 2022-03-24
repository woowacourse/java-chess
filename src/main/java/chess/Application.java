package chess;

import chess.domain.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        outputView.printGameStartMessage();

        String startOrEndInput = inputView.getStartOrEndInput();
        if (startOrEndInput.equals("start")) {
            startGame(inputView, outputView);
        }
        inputView.terminate();
    }

    private static void startGame(InputView inputView, OutputView outputView) {
        ChessGame game = new ChessGame();
        outputView.printBoard(game.getBoard());
        while (game.isRunning()) {
            playTurn(inputView, outputView, game);
            outputView.printBoard(game.getBoard());
        }
    }

    private static void playTurn(InputView inputView, OutputView outputView, ChessGame game) {
        try {
            String moveCommand = inputView.getMoveCommand();
            String[] commands = moveCommand.split(" ");
            game.movePiece(commands[1], commands[2]);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            playTurn(inputView, outputView, game);
        }
    }
}
