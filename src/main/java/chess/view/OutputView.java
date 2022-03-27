package chess.view;

import java.util.Map;

import chess.domain.board.coordinate.Column;
import chess.domain.board.coordinate.Coordinate;
import chess.domain.board.coordinate.Row;
import chess.domain.piece.Piece;

public class OutputView {

	private OutputView() {
	}

	public static void printGameStart() {
		System.out.println("체스 게임을 시작합니다.\n"
			+ "게임 시작 : start\n"
			+ "게임 종료 : end\n"
			+ "게임 이동 : move source위치 target위치 - 예. move b2 b3");
	}

	public static void printBoard(Map<Coordinate, Piece> init) {
		for (Row row : Row.values()) {
			for (Column column : Column.values()) {
				System.out.print(init.get(Coordinate.of(column, row)).getSymbol());
			}
			System.out.println();
		}
	}
}
