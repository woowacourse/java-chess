package controller;

import domain.Board;
import domain.ChessGame;
import domain.Location;
import java.util.ArrayList;
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
        chessGame.initialize();
        do {
            printBoard();
        } while (command(inputView.getCommand()));
    }

    private void printBoard() {
        final Board board = chessGame.getBoard();
        outputView.printBoard(board);
    }

    private boolean command(final List<String> command) {
        if (command.contains("end")) {
            return false;
        }
        if (command.contains("start")) {
            chessGame.initialize();
            return true;
        }
        move(command);
        return true;
    }

    private void move(final List<String> command) {
        List<Location> locations = makeLocations(command);
        chessGame.move(locations.get(0), locations.get(1));
    }

    private List<Location> makeLocations(final List<String> command) {
        final List<Location> locations = new ArrayList<>();
        for (int index = 1; index < 3; index++) {
            final String location = command.get(index);
            final int column = ColumnConverter.findColumn(location.substring(0, 1));
            final int row = Integer.parseInt(location.substring(1, 2));
            locations.add(Location.of(column, row));
        }
        return locations;
    }
}
