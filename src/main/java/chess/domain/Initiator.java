package chess.domain;

import chess.domain.position.Position;

import java.util.Map;

public interface Initiator {

    Map<Position, Piece> initiate();
}
