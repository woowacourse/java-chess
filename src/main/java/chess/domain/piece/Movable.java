package chess.domain.piece;

import chess.domain.board.BoardSquare;

import java.util.Map;
import java.util.Set;

@FunctionalInterface
public interface Movable {
    Set<BoardSquare> getCheatSheet(BoardSquare boardSquare, Map<BoardSquare, Piece> board);
}