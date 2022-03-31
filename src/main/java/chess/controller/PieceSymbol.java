package chess.controller;

import chess.domain.piece.Bishop;
import chess.domain.piece.Blank;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import java.util.Arrays;
import java.util.NoSuchElementException;

public enum PieceSymbol {

	KING(King.class, "♔", "♚"),
	QUEEN(Queen.class, "♕", "♛"),
	BISHOP(Bishop.class, "♗", "♝"),
	KNIGHT(Knight.class, "♘", "♞"),
	ROOK(Rook.class, "♖", "♜"),
	PAWN(Pawn.class, "♙", "♟"),
	BLANK(Blank.class, ".", ".");

	private static final String NOT_FOUND_SYMBOL = "해당 기물의 심볼이 없습니다.";
	
	private final Class<? extends Piece> pieceClass;
	private final String blackSymbol;
	private final String whiteSymbol;

	PieceSymbol(final Class<? extends Piece> pieceClass, final String blackSymbol, final String whiteSymbol) {
		this.pieceClass = pieceClass;
		this.blackSymbol = blackSymbol;
		this.whiteSymbol = whiteSymbol;
	}

	public static String of(Piece piece) {
		return Arrays.stream(values())
				.filter(pieceSymbol -> pieceSymbol.pieceClass.equals(piece.getClass()))
				.map(pieceSymbol -> convertByTeam(pieceSymbol, piece))
				.findAny()
				.orElseThrow(() -> new NoSuchElementException(NOT_FOUND_SYMBOL));
	}

	private static String convertByTeam(PieceSymbol pieceSymbol, Piece piece) {
		if (piece.isAlly(Team.BLACK)) {
			return pieceSymbol.blackSymbol;
		}
		return pieceSymbol.whiteSymbol;
	}
}
