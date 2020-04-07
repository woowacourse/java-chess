package chess.strategy;

import chess.piece.Piece;
import chess.position.Position;

import java.util.Map;

public interface PiecesInitStrategy {
    Map<Position, Piece> init();
}
