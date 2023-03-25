package chess.controller;

import chess.controller.command.Command;
import chess.domain.game.ChessGame;
import chess.dto.ChessBoardDto;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.function.Supplier;

public final class ChessController {

    public void run() {
        OutputView.printWelcomeMessage();
        ChessGame chessGame = new ChessGame();

        while (!chessGame.isGameEnd()) {
            playChessGame(chessGame);
            printChessBoard(chessGame);
        }
    }

    private void playChessGame(final ChessGame game) {
        Command command = getCommand();
        try {
            game.execute(command);
        } catch (Exception e) {
            OutputView.printErrorMessage(e.getMessage());
            playChessGame(game);
        }
    }

    private Command getCommand() {
        return repeatInput(InputView::readCommand);
    }

    private <T> T repeatInput(Supplier<T> input) {
        try {
            return input.get();
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return repeatInput(input);
        }
    }

    private void printChessBoard(final ChessGame chessGame) {
        OutputView.printChessBoard(new ChessBoardDto(chessGame.getChessBoard()));
    }
}
