package chess.domain.boardstrategy;

import chess.domain.Position;
import chess.domain.piece.Piece;

import java.util.Map;

public interface BoardStrategy {

    Map<Position, Piece> generate();
}
