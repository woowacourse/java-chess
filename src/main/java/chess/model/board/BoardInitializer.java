package chess.model.board;

import chess.model.piece.Piece;
import java.util.List;

public interface BoardInitializer {
    List<Piece> initPieces();
}
