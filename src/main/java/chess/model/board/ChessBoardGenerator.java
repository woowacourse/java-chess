package chess.model.board;

import chess.model.piece.Piece;
import chess.model.position.Position;

import java.util.Map;

public interface ChessBoardGenerator {
    Map<Position, Piece> create();
}
