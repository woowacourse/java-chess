package chess.view;

import chess.domain.Board;
import chess.domain.Status;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class OutputView {
	private static final String STATUS_FORMAT = "WHITE: %.1f\nBLACK: %.1f\nWINNER: %s\n";
	private static final String NEW_LINE = System.lineSeparator();

	public static void printGameStart() {
		System.out.println("체스 게임을 시작합니다." + NEW_LINE +
			"게임 시작 : start" + NEW_LINE +
			"게임 종료 : end " + NEW_LINE +
			"게임 이동 : move source -> target (예 : move b2 b3) ");
	}

	public static void printBoard(Board board) {
		board.getBoard()
			.forEach(OutputView::printPiece);
	}

	private static void printPiece(Position position, Piece piece) {
		System.out.print(drawPiece(piece));
		if (position.equalsX(8)) {
			System.out.println();
		}
	}

	private static String drawPiece(Piece piece) {
		if (piece.isBlack()) {
			return piece.getSymbol()
				.toUpperCase();
		}
		return piece.getSymbol();
	}

	public static void printStatus(Status status) {
		System.out.printf(STATUS_FORMAT, status.getWhiteScore(), status.getBlackScore(), status.winner());
	}
}

