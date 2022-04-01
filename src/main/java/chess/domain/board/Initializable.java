package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.Map;

@FunctionalInterface
public interface Initializable {

    Map<Position, Piece> init();
}
