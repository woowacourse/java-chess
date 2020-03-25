import chess.board.ChessBoard;
import view.InputView;
import view.OutputView;

public class ChessController {
	public static void main(String[] args) {
		if (InputView.inputStartCommand()) {
			ChessBoard chessBoard = new ChessBoard();
			OutputView.printBoard(chessBoard);
		}
	}
}
