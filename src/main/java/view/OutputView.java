package view;

import java.util.Map;

import chess.board.ChessBoard;
import chess.board.Location;
import chess.piece.type.Piece;

public class OutputView {

	private static final int MAXIMUM_BOARD_SIZE = 8;
	private static final char FIRST_COLUMN_VALUE = 'a';
	private static final String EMPTY_SHAPE = ".";

	public static void printBoard(ChessBoard chessBoard) {
		Map<Location, Piece> board = chessBoard.getBoard();

		for (int row = 0; row < MAXIMUM_BOARD_SIZE; row++) {
			for (int col = 0; col < MAXIMUM_BOARD_SIZE; col++) {
				Location reverseLocation = reverseLocation(row, col);
				System.out.print(findPieceOrDefault(board, reverseLocation));
			}
			System.out.println();
		}
	}

	private static Location reverseLocation(int row, int col) {
		int reversedRow = MAXIMUM_BOARD_SIZE - row;
		int reversedCol = FIRST_COLUMN_VALUE + col;
		return new Location(reversedRow, (char)reversedCol);
	}

	private static String findPieceOrDefault(Map<Location, Piece> board, Location target) {
		String value = EMPTY_SHAPE;
		if (board.containsKey(target)) {
			Piece piece = board.get(target);
			value = piece.toString();
		}
		return value;
	}

	public static void printInformation() {
		System.out.println("> 체스 게임을 시작합니다.\n"
			+ "> 게임 시작 : start\n"
			+ "> 게임 종료 : end\n"
			+ "> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
	}

	public static void printMoveErrorMessage() {
		System.out.println("이동할 수 없는 명령입니다. 다시 입력해주세요.");
	}
}
