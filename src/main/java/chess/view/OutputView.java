package chess.view;

import java.util.Locale;
import java.util.Map;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;

public class OutputView {

	public static void printStartMessage() {
		System.out.println("체스 게임을 시작합니다.");
		System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
	}

	public static void printErrorMessage(final String message) {
		System.out.println(message);
	}

	public static void printBoard(final Map<Position, Piece> pieces) {
		for (int i = 8; i >= 1; i--) {
			printRow(pieces, i);
			System.out.print(System.lineSeparator());
		}
	}

	private static void printRow(final Map<Position, Piece> pieces, int rawRow) {
		for (int i = 1; i <= 8; i++) {
			Position position = new Position(Row.of(rawRow), Column.of(i));
			Piece piece = pieces.getOrDefault(position, new EmptySpace());
			System.out.print(convertToSymbol(piece));
		}
	}

	private static String convertToSymbol(final Piece piece) {
		final Symbol symbol = Symbol.valueOf(piece.getName().toUpperCase(Locale.ROOT));
		if (piece.getColor() == Color.BLACK) {
			return symbol.getValue().toUpperCase(Locale.ROOT);
		}
		return symbol.getValue();
	}

}
