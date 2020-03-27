package chess.domain.movement;

import chess.domain.board.BoardSquare;
import chess.domain.piece.Piece;
import java.util.Map;
import java.util.Set;

@FunctionalInterface
public interface Movable {
    Set<BoardSquare> getCheatSheet(BoardSquare boardSquare, Map<BoardSquare, Piece> board);
}