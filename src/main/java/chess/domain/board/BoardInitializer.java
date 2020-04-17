package chess.domain.board;

import chess.domain.piece.PieceState;
import chess.domain.position.Position;

import java.util.Map;

public interface BoardInitializer {
    Map<Position, PieceState> create();
}
