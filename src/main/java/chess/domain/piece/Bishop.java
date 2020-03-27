package chess.domain.piece;

import chess.domain.board.BoardSquare;
import util.NullChecker;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Bishop extends Piece {
    private final static Map<Color, Piece> CACHE = new HashMap<>();
    private final static Type type = Type.BISHOP;

    static {
        for (Color color : Color.values()) {
            CACHE.put(color, new Bishop(color, type));
        }
    }

    public Bishop(Color color, Type type) {
        super(color, type);
    }

    public static Piece getPieceInstance(Color color) {
        NullChecker.validateNotNull(color);
        return CACHE.get(color);
    }

    @Override
    public Set<BoardSquare> getCheatSheet(BoardSquare boardSquare, Map<BoardSquare, Piece> board) {
        NullChecker.validateNotNull(boardSquare, board);
        Set<BoardSquare> boardSquares = getAllCheatSheet(boardSquare);
        Set<BoardSquare> squaresIter = getAllCheatSheet(boardSquare);
        for (BoardSquare s : squaresIter) {
            if (board.containsKey(s)) {
                int fileDifference = s.getFileSubtract(boardSquare);
                int rankDifference = s.getRankSubtract(boardSquare);

                if (fileDifference >= 1 && rankDifference >= 1) {
                    Set<BoardSquare> squaresToRemove = findSquaresToRemove(s, 1, 1);
                    boardSquares.removeAll(squaresToRemove);
                }

                if (fileDifference >= 1 && rankDifference <= -1) {
                    Set<BoardSquare> squaresToRemove = findSquaresToRemove(s, 1, -1);
                    boardSquares.removeAll(squaresToRemove);
                }

                if (fileDifference <= -1 && rankDifference >= 1) {
                    Set<BoardSquare> squaresToRemove = findSquaresToRemove(s, -1, 1);
                    boardSquares.removeAll(squaresToRemove);
                }

                if (fileDifference <= -1 && rankDifference <= -1) {
                    Set<BoardSquare> squaresToRemove = findSquaresToRemove(s, -1, -1);
                    boardSquares.removeAll(squaresToRemove);
                }

                if (isSameColor(board.get(s))) {
                    boardSquares.remove(s);
                }
            }
        }
        return boardSquares;

    }
}
