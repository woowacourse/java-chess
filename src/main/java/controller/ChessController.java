package controller;

import domain.Board;
import domain.ChessGame;
import domain.Location;
import java.util.List;
import view.InputView;
import view.OutputView;

public class ChessController {

    private final ChessGame chessGame;
    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(final ChessGame chessGame, final InputView inputView, final OutputView outputView) {
        this.chessGame = chessGame;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void play() {
        if (inputView.getEndIntent()) {
            return;
        }
        while (true) {
            printBoard();
            move();
        }
    }

    private void move() {
        final List<Location> moveLocations = inputView.getMoveLocations();
        chessGame.move(moveLocations.get(0), moveLocations.get(1));
    }

    private void printBoard() {
        final Board board = chessGame.getBoard();
        outputView.printBoard(board);
    }
}
