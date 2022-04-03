package chess.domain.boardstrategy;

import chess.domain.board.position.Position;
import chess.domain.piece.Piece;
import java.util.Map;

public interface BoardStrategy {
    Map<Position, Piece> create();

}
