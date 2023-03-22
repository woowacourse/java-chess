package controller;

import domain.game.Board;
import domain.game.ChessBoardGenerator;
import domain.game.ChessGame;
import view.InputView;
import view.OutputView;

import java.util.function.Supplier;

public class ChessController {

    private static final String GAME_EXIT_COMMAND = "end";

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        this.outputView.printGameGuideMessage();
        repeatByRunnable(inputView::requestStartCommand);
        Board chessBoard = new Board(new ChessBoardGenerator().generate());
        ChessGame chessGame = new ChessGame(chessBoard);

        this.outputView.printChessBoard(chessBoard.getChessBoard());
        while (move(chessGame)) {
            outputView.printChessBoard(chessBoard.getChessBoard());
        }

    }

    private boolean move(ChessGame chessGame) {
        String userCommand = repeatBySupplier(() -> inputView.requestUserCommandInGame());
        if (isExitGame(userCommand)) {
            return false;
        }
        try {
            chessGame.move(userCommand);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            move(chessGame);
        }
        return true;
    }

    private boolean isExitGame(String command) {
        return command.equals(GAME_EXIT_COMMAND);
    }

    private Runnable repeatByRunnable(Runnable runnable) {
        try {
            runnable.run();
            return runnable;
        } catch (IllegalArgumentException e) {
            this.outputView.printErrorMessage(e.getMessage());
            return repeatByRunnable(runnable);
        }
    }

    private String repeatBySupplier(Supplier<String> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            this.outputView.printErrorMessage(e.getMessage());
            return repeatBySupplier(supplier);
        }
    }
}
