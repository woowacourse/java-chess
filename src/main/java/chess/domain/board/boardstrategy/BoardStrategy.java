package chess.domain.board.boardstrategy;

import chess.domain.board.Position;
import chess.domain.piece.type.Piece;

import java.util.Map;

public interface BoardStrategy {

    Map<Position, Piece> generate();
}
