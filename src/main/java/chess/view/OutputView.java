package chess.view;

import java.util.Map;

import chess.domain.board.Board;
import chess.domain.game.Status;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;

public class OutputView {
	private static final String STATUS_FORMAT = "WHITE: %.1f BLACK: %.1f - %s진영 승\n";

	public static void printGameStart() {
		System.out.println("> 체스 게임을 시작합니다.");
		System.out.println("> 게임 시작 : start");
		System.out.println("> 게임 종료 : end");
		System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
	}

	public static void printBoard(Board board) {
		Map<Position, Piece> values = board.getBoard();
		for (int y = Position.END_Y - 1; y >= Position.BEGIN_Y; y--) {
			printFile(values, y);
		}
	}

	private static void printFile(Map<Position, Piece> values, int y) {
		for (int x = Position.BEGIN_X; x < Position.END_X; x++) {
			System.out.print(drawPiece(values.get(Position.of(x, y))));
		}
		System.out.println();
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
