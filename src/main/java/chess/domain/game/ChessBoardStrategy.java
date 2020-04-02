package chess.domain.game;

import chess.domain.chesspiece.Piece;
import chess.domain.position.Position;

import java.util.List;
import java.util.Map;

public interface ChessBoardStrategy {
    Map<Position, Piece> create(Map<Piece, List<Position>> initPieces);
}
