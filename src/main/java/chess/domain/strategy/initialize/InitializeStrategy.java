package chess.domain.strategy.initialize;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.Map;

public interface InitializeStrategy {
    Map<Position, Piece> initialize();

    Map<Position, Piece> webInitialize(Map<String, String> pieceOnBoards);
}
