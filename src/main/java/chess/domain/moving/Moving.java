package chess.domain.moving;

import chess.domain.board.Board;
import chess.domain.pieces.Piece;
import chess.domain.position.Position;

import java.util.List;

public interface Moving {
    List<Position> allMovablePositions(final Piece piece, final Board board);
}
