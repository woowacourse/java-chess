package chess.view;

import java.util.Map;

import chess.domain.Board;
import chess.domain.Status;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class OutputView {
	private static final String NEW_LINE = System.lineSeparator();

	public static void printGameStart() {
		System.out.println("체스 게임을 시작합니다." + NEW_LINE +
			"게임 시작 : start" + NEW_LINE +
			"게임 종료 : end " + NEW_LINE +
			"게임 이동 : move source -> target (예 : move b2 b3) ");
	}

	public static void printResponse(Board board) {
		Map<Position, Piece> gameBoard = board.getBoard();
		int count = 0;
		for (Piece value : gameBoard.values()) {
			if (count >= 16 && count <= 48) {
				System.out.print("[ ]");
			} else {
				System.out.print(value.toString() + " ");
			}
			count++;
			if (count % 8 == 0) {
				System.out.println();
			}
		}
	}

	private static void printPiece(Position position, Piece piece) {
		System.out.print(piece.toString() + " ");
		if (position.equalsX(8)) {
			System.out.println();
		}
	}

	public static void printStatus(Status status) {
		System.out.printf("WHITE : %.1f \nBLACK : %.1f \nWINNER : %s\n",
			status.getWhiteScore(), status.getBlackScore(), status.winner());
	}
}
