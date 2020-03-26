import chess.board.ChessBoard;
import chess.board.Location;
import view.InputView;
import view.OutputView;

public class ChessController {
	public static void main(String[] args) {
		OutputView.printInformation();
		String command = InputView.inputStartCommand();
		ChessBoard chessBoard = new ChessBoard();
		OutputView.printBoard(chessBoard);
		while (command.equals("start") || command.substring(0, 4).equals("move")) {
			command = InputView.inputStartCommand();

			char nowCol = command.split(" ")[1].charAt(0);
			int nowRow = command.split(" ")[1].charAt(1) - '0';
			char destCol = command.split(" ")[2].charAt(0);
			int destRow = command.split(" ")[2].charAt(1) - '0';

			Location now = new Location(nowRow, nowCol);
			Location destination = new Location(destRow, destCol);
			while (!chessBoard.canMove(now, destination)) {
				System.out.println("이동할 수 없는 명령입니다.");
				command = InputView.inputStartCommand();
			}
			chessBoard.move(now, destination);
			OutputView.printBoard(chessBoard);
		}
	}
}
