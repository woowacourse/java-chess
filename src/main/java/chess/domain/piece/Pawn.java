package chess.domain.piece;

import chess.domain.board.BoardSquare;
import chess.domain.board.ChessBoard;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import util.NullChecker;

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
        Set<BoardSquare> allCheatSheet = getAllCheatSheet(boardSquare);
        Set<BoardSquare> totalCheatSheet = new HashSet<>();
        boolean initialPoint = ChessBoard.isInitialPoint(boardSquare, this);
        for (BoardSquare cheatSheet : allCheatSheet) {
            if (!board.containsKey(cheatSheet)) {
                BoardSquare oneMore = cheatSheet
                    .addIfInBoundary(0, cheatSheet.getRankCompare(boardSquare));
                if (initialPoint && !board.containsKey(oneMore)) {
                    totalCheatSheet.add(oneMore);
                }
                totalCheatSheet.add(cheatSheet);
            }
        }
        totalCheatSheet.addAll(getDiagonalCheatSheet(board, allCheatSheet));
        return totalCheatSheet;
    }

    private Set<BoardSquare> getDiagonalCheatSheet(Map<BoardSquare, Piece> board,
        Set<BoardSquare> allCheatSheet) {
        Set<BoardSquare> diagonalCheatSheet = new HashSet<>();
        for (BoardSquare cheatSheet : allCheatSheet) {
            BoardSquare cheatSheetRight = cheatSheet.addIfInBoundary(-1, 0);
            BoardSquare cheatSheetLeft = cheatSheet.addIfInBoundary(1, 0);
            addDiagonalCheatSheet(board, diagonalCheatSheet, cheatSheetRight);
            addDiagonalCheatSheet(board, diagonalCheatSheet, cheatSheetLeft);
        }
        return diagonalCheatSheet;
    }

    private void addDiagonalCheatSheet(Map<BoardSquare, Piece> board,
        Set<BoardSquare> diagonalCheatSheet, BoardSquare cheatSheet) {
        if (board.containsKey(cheatSheet) && !isSameColor(board.get(cheatSheet))) {
            diagonalCheatSheet.add(cheatSheet);
        }
    }
}
