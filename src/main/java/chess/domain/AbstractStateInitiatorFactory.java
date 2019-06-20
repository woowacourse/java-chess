package chess.domain;

import java.util.Map;

public interface AbstractStateInitiatorFactory {
    Map<ChessCoordinate, ChessPiece> create();
}
