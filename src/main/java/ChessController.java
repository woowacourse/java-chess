import chess.board.ChessBoard;
import chess.board.Location;
import view.InputView;
import view.OutputView;

public class ChessController {
	public static void main(String[] args) {

		OutputView.printInformation();
		String command = InputView.inputStartCommand();
		ChessBoard chessBoard = new ChessBoard();
		while (command.equals("start") || command.substring(4).equals("move")) {
			OutputView.printBoard(chessBoard);
			command = InputView.inputStartCommand();
			char nowCol = command.split(" ")[1].charAt(0);
			int nowRow = command.split(" ")[1].charAt(1) - '0';
			char destCol = command.split(" ")[2].charAt(0);
			int destRow = command.split(" ")[2].charAt(1) - '0';

			while (!chessBoard.canMove(new Location(nowRow, nowCol), new Location(destRow, destCol))) {
				command = InputView.inputStartCommand();
			}
		}
	}
}
