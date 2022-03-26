package chess.model.boardinitializer;

import java.util.Map;

import chess.model.piece.Piece;
import chess.vo.Position;

public interface BoardInitializer {
    Map<Position, Piece> apply();
}
