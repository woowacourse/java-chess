package chess.model.board;

import chess.model.piece.Piece;
import java.util.Map;

public interface BoardInitializer {
    Map<Square, Piece> initPieces();
}
