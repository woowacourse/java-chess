package chess.domain;

import chess.domain.piece.Piece;
import java.util.Map;

public interface PiecesGenerator {

    Map<Position, Piece> generate();
}
