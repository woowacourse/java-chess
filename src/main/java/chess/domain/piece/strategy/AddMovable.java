package chess.domain.piece.strategy;

import chess.domain.Direction;
import chess.domain.board.Square;
import chess.domain.piece.Piece;

import java.util.Map;
import java.util.Set;

public interface AddMovable {

    void addMovable(Map<Square, Piece> chessBoard, Set<Square> availableSquares, Direction direction);
}
