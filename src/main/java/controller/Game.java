package controller;

import domain.board.Board;
import domain.command.Command;
import view.InputView;
import view.OutputView;

public class Game {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void play() {

        outputView.printStartNotice();
        Command command = inputView.readCommand();

        Board board = Board.create();
        outputView.printInitBoard(board);
    }
}
