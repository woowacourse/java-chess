package chess.domain.board;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.domain.board.coordinate.Column;
import chess.domain.board.coordinate.Coordinate;
import chess.domain.board.coordinate.Row;
import chess.domain.piece.Bishop;
import chess.domain.piece.Dot;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.Symbol;
import chess.domain.piece.Team;

public enum InitialBoard {
	BLACK_BISHOP(Arrays.asList(Column.C, Column.F), Arrays.asList(Row.EIGHT), new Bishop(Symbol.BISHOP, Team.BLACK)),
	WHITE_BISHOP(Arrays.asList(Column.C, Column.F), Arrays.asList(Row.ONE), new Bishop(Symbol.BISHOP, Team.WHITE)),
	BLACK_KING(Arrays.asList(Column.E), Arrays.asList(Row.EIGHT), new King(Symbol.KING, Team.BLACK)),
	WHITE_KING(Arrays.asList(Column.E), Arrays.asList(Row.ONE), new King(Symbol.KING, Team.WHITE)),
	BLACK_KNIGHT(Arrays.asList(Column.B, Column.G), Arrays.asList(Row.EIGHT), new Knight(Symbol.KNIGHT, Team.BLACK)),
	WHITE_KNIGHT(Arrays.asList(Column.B,Column.G), Arrays.asList(Row.ONE), new Bishop(Symbol.KNIGHT, Team.BLACK)),
	BLACK_PAWN(Arrays.asList(Column.values()), Arrays.asList(Row.SEVEN), new Pawn(Symbol.PAWN, Team.BLACK)),
	WHITE_PAWN(Arrays.asList(Column.values()), Arrays.asList(Row.TWO), new Pawn(Symbol.PAWN, Team.WHITE)),
	BLACK_QUEEN(Arrays.asList(Column.D), Arrays.asList(Row.EIGHT), new Queen(Symbol.QUEEN, Team.BLACK)),
	WHITE_QUEEN(Arrays.asList(Column.D), Arrays.asList(Row.ONE), new Queen(Symbol.QUEEN, Team.WHITE)),
	BLACK_ROOK(Arrays.asList(Column.A, Column.H), Arrays.asList(Row.EIGHT), new Rook(Symbol.ROOK, Team.BLACK)),
	WHITE_ROOK(Arrays.asList(Column.A, Column.H), Arrays.asList(Row.ONE), new Rook(Symbol.ROOK, Team.WHITE)),
	DOT(Arrays.asList(Column.values()),
		Arrays.asList(Row.THREE, Row.FOUR, Row.FIVE, Row.SIX), new Dot(Symbol.DOT, Team.NONE));

	private final List<Column> columns;
	private final List<Row> rows;
	private final Piece piece;

	InitialBoard(List<Column> columns, List<Row> rows, Piece piece) {
		this.columns = columns;
		this.rows = rows;
		this.piece = piece;
	}

	public static Map<Coordinate, Piece> init() {
		Map<Coordinate, Piece> map = new HashMap<>();
		for (Column column : Column.values()) {
			for (Row row : Row.values()) {
				map.put(Coordinate.of(column, row), findPiece(column, row));
			}
		}
		return map;
	}

	private static Piece findPiece(Column column, Row row) {
		return Arrays.stream(InitialBoard.values())
			.filter(board -> board.columns.contains(column))
			.filter(board -> board.rows.contains(row))
			.findFirst()
			.map(board -> board.piece)
			.orElseThrow(() -> new IllegalArgumentException("찾을 수 없습니다."));
	}
}
