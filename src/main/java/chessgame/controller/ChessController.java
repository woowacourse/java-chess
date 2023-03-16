package chessgame.controller;

import chessgame.domain.Board;
import chessgame.domain.point.File;
import chessgame.domain.point.Point;
import chessgame.domain.point.Rank;
import chessgame.util.ChessBoardFactory;
import chessgame.view.InputView;
import chessgame.view.OutputView;

public class ChessController {
    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printStartMessage();
        Board board = new Board(ChessBoardFactory.create());
        outputView.printChessBoard(board);
        while (true) {
            String[] input = inputView.readCommand().split(" ");
            if (input[0].equals("move")) {
                board.move(Point.of(File.findFile(input[1].charAt(0)).get(),
                        Rank.findRank(Integer.parseInt(input[1].substring(1, 2))).get()),
                    Point.of(File.findFile(input[2].charAt(0)).get(),
                        Rank.findRank(Integer.parseInt(input[2].substring(1, 2))).get()));
            }
            outputView.printChessBoard(board);
        }
    }
}
