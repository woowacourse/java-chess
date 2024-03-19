package controller;

import domain.ChessBoard;
import domain.piece.PiecesGenerator;
import dto.BoardStatus;
import view.Command;
import view.InputView;
import view.OutputView;

public class ChessGame {
    private final InputView inputView;
    private final OutputView outputView;

    public ChessGame(final InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        Command command = inputView.readCommand();
        BoardStatus boardStatus = new ChessBoard(PiecesGenerator.getInstance()).status();
        outputView.printChessBoard(boardStatus);
    }
}
