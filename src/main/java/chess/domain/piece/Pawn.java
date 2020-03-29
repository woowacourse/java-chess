package chess.domain.piece;

import chess.domain.board.BoardSquare;
import chess.domain.board.ChessBoard;
import chess.domain.board.ChessInitialSetting;
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
    public Set<BoardSquare> getCheatSheet(BoardSquare boardSquare, Map<BoardSquare, Piece> board,
        Set<ChessInitialSetting> castlingElements) {
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
            straightCheatSheet.addAll(getFrontCheatSheet(boardSquare, board, oneMore));
            straightCheatSheet.add(cheatSheet);
        }
        return straightCheatSheet;
    }

    private Set<BoardSquare> getFrontCheatSheet(BoardSquare boardSquare,
        Map<BoardSquare, Piece> board, BoardSquare oneMore) {
        Set<BoardSquare> frontCheatSheet = new HashSet<>();
        boolean initialPoint = ChessBoard.isInitialPoint(boardSquare, this);
        boolean containsOneMore = !board.containsKey(oneMore);
        if (initialPoint && containsOneMore) {
            frontCheatSheet.add(oneMore);
        }
        return frontCheatSheet;
    }

    private Set<BoardSquare> getDiagonalCheatSheet(Map<BoardSquare, Piece> board,
        Set<BoardSquare> allCheatSheet) {
        Set<BoardSquare> diagonalCheatSheet = new HashSet<>();
        for (BoardSquare cheatSheet : allCheatSheet) {
            diagonalCheatSheet.add(cheatSheet.getAddIfInBoundaryOrMyself(-1, 0));
            diagonalCheatSheet.add(cheatSheet.getAddIfInBoundaryOrMyself(1, 0));
            diagonalCheatSheet.remove(cheatSheet);
        }
        return diagonalCheatSheet.stream()
            .filter(board::containsKey)
            .filter(cheatSheet -> !isSameColor(board.get(cheatSheet)))
            .collect(Collectors.toSet());
    }
}
