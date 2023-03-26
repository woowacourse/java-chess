package chess.domain.piece;

import chess.domain.piece.type.Piece;
import java.util.List;

public interface PiecesGenrator {
    List<Piece> generate();
}
