package chess.domain.piece;

import chess.domain.board.BoardSquare;
import util.NullChecker;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Pawn extends Piece {
    private final static Map<Color, Piece> CACHE = new HashMap<>();
    private final static Type type = Type.PAWN;

    static {
        for (Color color : Color.values()) {
            CACHE.put(color, new Pawn(color, type));
        }
    }

    public Pawn(Color color, Type type) {
        super(color, type);
    }

    public static Piece getPieceInstance(Color color) {
        NullChecker.validateNotNull(color);
        return CACHE.get(color);
    }

    @Override
    public Set<BoardSquare> getCheatSheet(BoardSquare boardSquare, Map<BoardSquare, Piece> board) {
        Set<BoardSquare> boardSquares = getAllCheatSheet(boardSquare);
        for (BoardSquare s : boardSquares) {
            BoardSquare boardSquareRight = s.addIfInBoundary(-1, 0);
            BoardSquare boardSquareLeft = s.addIfInBoundary(1, 0);
            if (boardSquare.isPawnStartPoint(isBlack())) {
                boardSquares.add(s.addIfInBoundary(0, s.getRankCompare(boardSquare)));
            }
            if (board.containsKey(s)) {
                boardSquares.removeAll(getAllCheatSheet(boardSquare));
            }
            if (board.containsKey(boardSquareRight) && !isSameColor(board.get(boardSquareRight))) {
                boardSquares.add(boardSquareRight);
            }
            if (board.containsKey(boardSquareLeft) && !isSameColor(board.get(boardSquareLeft))) {
                boardSquares.add(boardSquareLeft);
            }
            if (board.containsKey(s) && isSameColor(board.get(s))) {
                boardSquares.remove(s);
            }
        }
        return boardSquares;
    }
}
