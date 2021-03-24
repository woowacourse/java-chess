package chess.domain.move;

import chess.domain.board.Board;
import chess.domain.pieces.Piece;
import chess.domain.position.Position;

import java.util.List;

public interface Movable {
    List<Position> allMovablePosition(Piece piece, Board board, int[] rowDir, int[] colDir);
}
