package chess;

import chess.view.InputView;
import chess.view.ResultView;
import java.util.List;

public class ChessConsole {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        ResultView resultView = new ResultView();
        Board board = BoardFactory.makeBoard();

        while (!board.isFinished()){
            resultView.showBoard(board);
            List<Position> positions = inputView.readPosition();
            board.move(positions.getFirst(), positions.getLast());
            resultView.showBoard(board);
        }

    }
}
