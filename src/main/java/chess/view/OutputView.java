package chess.view;

import java.util.Map;

import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;

public class OutputView {

	private OutputView() {
	}

	public static void printGuideMessage() {
		System.out.println("체스 게임을 시작합니다.");
		System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
	}

	public static void printBoard(Map<Position, String> board) {
		for (Column column : Column.reverseColumns()) {
			printColumnWithRow(board, column);
			System.out.println();
		}
	}

	private static void printColumnWithRow(Map<Position, String> board, Column column) {
		for (Row row : Row.values()) {
			Position position = new Position(row, column);
			String piece = board.get(position);
			printStringOrDefault(piece);
		}
	}

	private static void printStringOrDefault(String piece) {
		if (piece != null) {
			System.out.print(piece);
			return;
		}
		System.out.print(".");
	}
}
