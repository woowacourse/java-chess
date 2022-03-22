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
		System.out.println("체스 게임을 시작합니다.");
	}

	public static void printInitialBoard(Map<Coordinate, Piece> init) {
		for (Row row : Row.values()) {
			for (Column column : Column.values()) {
				System.out.print(init.get(Coordinate.of(column, row)).getSymbol());
			}
			System.out.println();
		}
	}
}
