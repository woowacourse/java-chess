package chess.domain.piece.generator;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;

public interface PiecesGenerator {

    Map<Position, Piece> generate();
}
