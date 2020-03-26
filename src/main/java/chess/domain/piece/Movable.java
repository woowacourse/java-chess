package chess.domain.piece;

import chess.domain.board.Square;

import java.util.Map;
import java.util.Set;

@FunctionalInterface
public interface Movable {
    Set<Square> getCheatSheet(Square square, Map<Square, Piece> board);
}