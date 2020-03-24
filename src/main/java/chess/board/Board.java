package chess.board;

import chess.piece.Piece;

import java.util.Map;

public interface Board {
    Map<Location, Piece> giveMyPiece(final boolean team);
}
