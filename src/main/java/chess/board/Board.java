package chess.board;

import java.util.Map;

import chess.piece.Piece;

public interface Board {
	Map<Location, Piece> giveMyPiece(final boolean team);
}
