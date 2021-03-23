package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;

public interface LocationInitializer {

    Map<Position, Piece> initialize();
}
