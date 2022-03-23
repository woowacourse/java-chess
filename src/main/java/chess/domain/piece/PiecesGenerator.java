package chess.domain.piece;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;

public interface PiecesGenerator {

    Map<Position, Piece> generate();
}
