package chess.model.domain.board;

import chess.model.domain.piece.Piece;
import java.util.Map;

public interface BoardInitialization {

    Map<BoardSquare, Piece> getInitialize();
}
