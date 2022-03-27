package chess;

import java.util.ArrayList;
import java.util.List;

import chess.domain.Board;
import chess.domain.position.Square;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGame {
    public void run() {
        OutputView.announceStart();

        if (!InputView.isStart()) {
            System.exit(0);
        }

        Board board = new Board();
        OutputView.showBoard(board.splitByRank());

        List<String> squares = InputView.requireCommand();
        while (squares.size() != 0) {
            movePiece(board, squares);
            squares = checkGameOver(board);
        }
    }

    private List<String> checkGameOver(Board board) {
        if (board.isGameOver()) {
            return new ArrayList<>();
        }
        return InputView.requireCommand();
    }

    private void movePiece(Board board, List<String> squares) {
        try {
            String source = squares.get(0);
            String target = squares.get(1);
            board = board.move(new Square(source), new Square(target));
            OutputView.showBoard(board.splitByRank());
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
        }
    }
}
