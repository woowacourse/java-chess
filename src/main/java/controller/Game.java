package controller;

import domain.board.Board;
import view.OutputView;

public class Game {

    private final OutputView outputView = new OutputView();

    public void play() {
        Board board = Board.create();
        outputView.printInitBoard(board);
    }
}
