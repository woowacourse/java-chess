package chess.view;

import chess.domain.game.Board;
import chess.domain.game.Status;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;

public class OutputView {
	private static final String STATUS_FORMAT = "WHITE: %.1f\nBLACK: %.1f\nWINNER: %s\n";

	public static void printGameStart() {
		System.out.println("> 체스 게임을 시작합니다.");
		System.out.println("> 게임 시작 : start");
		System.out.println("> 게임 종료 : end");
		System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
	}

	public static void printBoard(Board board) {
		board.getBoard()
				.forEach(OutputView::printPiece);
	}

	private static void printPiece(Position position, Piece piece) {
		System.out.print(drawPiece(piece));
		if (position.equalsX(Position.END_X - 1)) {
			System.out.println();
		}
	}

	private static String drawPiece(Piece piece) {
		if (piece.isBlack()) {
			return piece.symbol()
					.toUpperCase();
		}
		return piece.symbol();
	}

	public static void printStatus(Status status) {
		System.out.printf(STATUS_FORMAT, status.getWhiteScore(), status.getBlackScore(), status.winner());
	}
}
