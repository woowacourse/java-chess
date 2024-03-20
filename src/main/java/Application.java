import java.util.Arrays;
import java.util.List;

import domain.ChessGame;
import domain.Piece;
import domain.Position;
import view.InputView;
import view.OutputView;

//Todo: 메서드 분리, 클래스 분리
public class Application {
	public static void main(String[] args) {
		OutputView outputView = new OutputView();
		InputView inputView = new InputView();

		outputView.printGuide();
		String startOrEnd = inputView.readStartOrEnd();
		if (startOrEnd.equals("end")) {
			return;
		}
		ChessGame chessGame = new ChessGame();
		List<Piece> piecesOnBoard = chessGame.getPiecesOnBoard();
		outputView.printChessBoard(piecesOnBoard);
		String endOrMove = inputView.readEndOrMove();
		while (endOrMove.startsWith("move")) {
			playGame(outputView, endOrMove, chessGame);
			endOrMove = inputView.readEndOrMove();
		}
	}

	private static void playGame(OutputView outputView, String endOrMove, ChessGame chessGame) {
		List<Position> positions = Arrays.stream(endOrMove.split(" ")).skip(1)
			.map(String::toUpperCase)
			.map(Position::valueOf)
			.toList();
		chessGame.move(positions.get(0), positions.get(1));

		List<Piece> piecesOnBoard = chessGame.getPiecesOnBoard();
		outputView.printChessBoard(piecesOnBoard);
	}
}
