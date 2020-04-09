package web.util;

import static domain.board.Board.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.board.Rank;
import domain.piece.Bishop;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Queen;
import domain.piece.Rook;
import domain.piece.position.Position;
import domain.piece.team.Team;

public class BoardConverter {
	private static final String EMPTY_SYMBOL = ".";

	public static List<Rank> toBoardFromSymbol(String symbol) {
		List<Rank> board = new ArrayList<>();
		List<Piece> pieces = new ArrayList<>();
		int row = 1;
		int column = 0;

		for (int i = 0; i < symbol.length(); i++) {
			if (i!=0 && i % 8 == 0) {
				board.add(new Rank(pieces));
				pieces = new ArrayList<>();
				row++;
				column = 0;
			}
			char piece = symbol.charAt(i);
			column++;
			if (symbol.charAt(i) == '.') {
				continue;
			}

			if (piece == 'p') {
				pieces.add(new Pawn(Position.of(column, row), Team.WHITE));
			}
			if (piece == 'P') {
				pieces.add(new Pawn(Position.of(column, row), Team.BLACK));
			}
			if (piece == 'r') {
				pieces.add(new Rook(Position.of(column, row), Team.WHITE));
			}
			if (piece == 'R') {
				pieces.add(new Rook(Position.of(column, row), Team.BLACK));
			}
			if (piece == 'b') {
				pieces.add(new Bishop(Position.of(column, row), Team.WHITE));
			}
			if (piece == 'B') {
				pieces.add(new Bishop(Position.of(column, row), Team.BLACK));
			}
			if (piece == 'n') {
				pieces.add(new Knight(Position.of(column, row), Team.WHITE));
			}
			if (piece == 'N') {
				pieces.add(new Knight(Position.of(column, row), Team.BLACK));
			}
			if (piece == 'q') {
				pieces.add(new Queen(Position.of(column, row), Team.WHITE));
			}
			if (piece == 'Q') {
				pieces.add(new Queen(Position.of(column, row), Team.BLACK));
			}
			if (piece == 'k') {
				pieces.add(new King(Position.of(column, row), Team.WHITE));
			}
			if (piece == 'K') {
				pieces.add(new King(Position.of(column, row), Team.BLACK));
			}
		}
		board.add(new Rank(pieces));
		return board;
	}

	public static List<String> boardToUnicode(List<String> board) {
		return board.stream()
			.map(UnicodeConverter::toUnicodeFrom)
			.collect(Collectors.toList());
	}

	public static String toSymbolFromBoard(List<Rank> Board) {
		List<String> symbols = toSymbolsFromBoard(Board);
		return toSymbolFromSymbols(symbols);
	}

	public static List<String> toSymbolsFromBoard(List<Rank> board) {
		List<String> symbols = new ArrayList<>();
		for (Rank rank : board) {
			for (int i = MIN_COLUMN_COUNT; i <= MAX_COLUMN_COUNT; i++) {
				final int columnNumber = i;
				String pieceSymbol = rank.getPieces().stream()
					.filter(p -> p.equalsColumn(columnNumber))
					.map(Piece::showSymbol)
					.findFirst()
					.orElse(EMPTY_SYMBOL);
				symbols.add(pieceSymbol);
			}
		}
		return symbols;
	}

	public static String toSymbolFromSymbols(List<String> inputSymbol) {
		return String.join("", inputSymbol);
	}

	public static List<String> toSymbolsFromSymbol(String inputSymbol) {
		List<String> symbols = new ArrayList<>();
		for (int i = 0; i < inputSymbol.length(); i++) {
			symbols.add(String.valueOf(inputSymbol.charAt(i)));
		}
		return symbols;
	}
}
