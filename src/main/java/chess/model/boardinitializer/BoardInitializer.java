package chess.model.boardinitializer;

import java.util.Map;

import chess.model.Position;
import chess.model.piece.Piece;

public interface BoardInitializer {
    Map<Position, Piece> apply();
}
