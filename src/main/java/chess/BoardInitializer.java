package chess;

import java.util.Map;

import chess.piece.Piece;

public interface BoardInitializer {
    Map<Position, Piece> apply();
}
