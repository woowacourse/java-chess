package chess.domain.piece;

import chess.domain.board.BoardSquare;
import chess.domain.board.ChessBoard;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import util.NullChecker;

public class Pawn extends OneTimeMovePiece {

    private final static Map<Color, Piece> CACHE = new HashMap<>();

    static {
        for (Color color : Color.values()) {
            CACHE.put(color, new Pawn(color, Type.PAWN));
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
        Set<BoardSquare> containsCheatSheet = allCheatSheet.stream()
            .filter(cheatSheet -> !board.containsKey(cheatSheet))
            .collect(Collectors.toSet());
        Set<BoardSquare> totalCheatSheet = new HashSet<>();
        totalCheatSheet.addAll(getStraightCheatSheet(boardSquare, board, containsCheatSheet));
        totalCheatSheet.addAll(getDiagonalCheatSheet(board, allCheatSheet));
        return totalCheatSheet;
    }

    private Set<BoardSquare> getStraightCheatSheet(BoardSquare boardSquare,
        Map<BoardSquare, Piece> board, Set<BoardSquare> containsCheatSheet) {
        Set<BoardSquare> straightCheatSheet = new HashSet<>();
        for (BoardSquare cheatSheet : containsCheatSheet) {
            BoardSquare oneMore = cheatSheet
                .getAddIfInBoundaryOrMyself(0, cheatSheet.getRankCompare(boardSquare));
            straightCheatSheet.addAll(getOneMoreCheatSheet(boardSquare, board, oneMore));
            straightCheatSheet.add(cheatSheet);
        }
        return straightCheatSheet;
    }

    private Set<BoardSquare> getOneMoreCheatSheet(BoardSquare boardSquare,
        Map<BoardSquare, Piece> board, BoardSquare oneMore) {
        Set<BoardSquare> oneMoreCheatSheet = new HashSet<>();
        boolean initialPoint = ChessBoard.isInitialPoint(boardSquare, this);
        boolean containsOneMore = !board.containsKey(oneMore);
        if (initialPoint && containsOneMore) {
            oneMoreCheatSheet.add(oneMore);
        }
        return oneMoreCheatSheet;
    }

    private Set<BoardSquare> getDiagonalCheatSheet(Map<BoardSquare, Piece> board,
        Set<BoardSquare> allCheatSheet) {
        Set<BoardSquare> diagonalCheatSheet = new HashSet<>();
        for (BoardSquare cheatSheet : allCheatSheet) {
            BoardSquare cheatSheetRight = cheatSheet.getAddIfInBoundaryOrMyself(-1, 0);
            BoardSquare cheatSheetLeft = cheatSheet.getAddIfInBoundaryOrMyself(1, 0);
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
