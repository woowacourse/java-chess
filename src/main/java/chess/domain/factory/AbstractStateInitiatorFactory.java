package chess.domain.factory;

import chess.domain.coordinate.ChessCoordinate;
import chess.domain.piece.ChessPiece;

import java.util.Map;

@FunctionalInterface
public interface AbstractStateInitiatorFactory {
    Map<ChessCoordinate, ChessPiece> create();
}
