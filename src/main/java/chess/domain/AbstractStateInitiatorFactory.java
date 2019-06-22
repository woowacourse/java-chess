package chess.domain;

import java.util.Map;

public interface AbstractStateInitiatorFactory {
    Map<CoordinatePair, ChessPiece> create();
}
