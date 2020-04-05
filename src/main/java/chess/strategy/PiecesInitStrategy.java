package chess.strategy;

import chess.piece.Piece;
import chess.position.Position;

import java.util.Map;

public interface PiecesInitStrategy {
    public Map<Position, Piece> init();
}
