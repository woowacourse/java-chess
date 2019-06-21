package chess.domain;

import java.util.Map;

@FunctionalInterface
public interface AbstractStateInitiatorFactory {
    Map<ChessCoordinate, ChessPiece> create();
}
