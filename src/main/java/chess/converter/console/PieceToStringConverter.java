package chess.converter.console;

import java.util.Arrays;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;

public enum PieceToStringConverter {

	BLACK_KING(King.createBlack(), "♔"),
	WHITE_KING(King.createWhite(), "♚"),
	BLACK_QUEEN(Queen.createBlack(), "♕"),
	WHITE_QUEEN(Queen.createWhite(), "♛"),
	BLACK_BISHOP(Bishop.createBlack(), "♗"),
	WHITE_BISHOP(Bishop.createWhite(), "♝"),
	BLACK_KNIGHT(Knight.createBlack(), "♘"),
	WHITE_KNIGHT(Knight.createWhite(), "♞"),
	BLACK_ROOK(Rook.createBlack(), "♖"),
	WHITE_ROOK(Rook.createWhite(), "♜"),
	BLACK_PAWN(Pawn.createBlack(), "♙"),
	WHITE_PAWN(Pawn.createWhite(), "♟");

	private static final String NOT_HAVE_SYMBOL = "심볼이 없는 피스입니다.";

	private final Piece piece;
	private final String symbol;

	PieceToStringConverter(Piece piece, String symbol) {
		this.piece = piece;
		this.symbol = symbol;
	}

	public static String from(Piece piece) {
		return Arrays.stream(values())
			.filter(pieceView -> pieceView.piece.equals(piece))
			.map(pieceView -> pieceView.symbol)
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException(NOT_HAVE_SYMBOL));
	}
}
