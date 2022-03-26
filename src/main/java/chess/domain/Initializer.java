package chess.domain;

import chess.domain.position.Position;

import java.util.Map;

public interface Initializer {

    Map<Position, Piece> initialize();
}
