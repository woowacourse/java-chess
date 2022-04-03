package chess.repository.converter;

import java.util.Arrays;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;

public enum StringToPieceConverter {

	BLACK_KING(King.createBlack()),
	WHITE_KING(King.createWhite()),
	BLACK_QUEEN(Queen.createBlack()),
	WHITE_QUEEN(Queen.createWhite()),
	BLACK_BISHOP(Bishop.createBlack()),
	WHITE_BISHOP(Bishop.createWhite()),
	BLACK_KNIGHT(Knight.createBlack()),
	WHITE_KNIGHT(Knight.createWhite()),
	BLACK_ROOK(Rook.createBlack()),
	WHITE_ROOK(Rook.createWhite()),
	BLACK_PAWN(Pawn.createBlack()),
	WHITE_PAWN(Pawn.createWhite());

	private static final String NOT_EXIST_NAME = "없는 피스 이름입니다.";

	private final Piece piece;

	StringToPieceConverter(Piece piece) {
		this.piece = piece;
	}

	public static Piece from(String name) {
		return Arrays.stream(values())
			.filter(each -> each.name().equals(name))
			.map(each -> each.piece)
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException(NOT_EXIST_NAME));
	}
}
