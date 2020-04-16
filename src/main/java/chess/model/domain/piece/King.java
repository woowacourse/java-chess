package chess.model.domain.piece;

import chess.model.domain.board.BoardSquare;
import chess.model.domain.board.CastlingSetting;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import util.NullChecker;

public class King extends OneTimeMovePiece {

    private final static Map<Color, Piece> CACHE = new HashMap<>();

    static {
        CACHE.put(Color.BLACK, new King(Color.BLACK, Type.KING));
        CACHE.put(Color.WHITE, new King(Color.WHITE, Type.KING));
    }

    public King(Color color, Type type) {
        super(color, type);
    }

    public static Piece getPieceInstance(Color color) {
        NullChecker.validateNotNull(color);
        return CACHE.get(color);
    }

    @Override
    public Set<BoardSquare> getCheatSheet(BoardSquare boardSquare, Map<BoardSquare, Piece> board,
        Set<CastlingSetting> castlingElements) {
        Set<BoardSquare> cheatSheet = getAllCheatSheet(boardSquare).stream()
            .filter(s -> !(board.containsKey(s) && isSameColor(board.get(s))))
            .collect(Collectors.toSet());
        cheatSheet.addAll(getCastlingCheatSheet(boardSquare, board, castlingElements));
        return cheatSheet;
    }

    private Set<BoardSquare> getCastlingCheatSheet(BoardSquare boardSquare,
        Map<BoardSquare, Piece> board, Set<CastlingSetting> castlingElements) {
        Set<CastlingSetting> sameColorCastlingElements = castlingElements.stream()
            .filter(castlingElement -> castlingElement.isSameColor(this))
            .collect(Collectors.toSet());
        Set<BoardSquare> castlingCheatSheets = CastlingSetting
            .getCastlingCheatSheets(sameColorCastlingElements);

        Set<BoardSquare> totalCheatSheet = new HashSet<>();
        for (BoardSquare castlingCheatSheet : castlingCheatSheets) {
            int fileCompare = boardSquare.getFileCompare(castlingCheatSheet);
            Set<BoardSquare> boardSquaresForCastling = IntStream
                .range(1, BoardSquare.MAX_FILE_AND_RANK_COUNT)
                .filter(index -> boardSquare.hasIncreased(fileCompare * -index, 0))
                .mapToObj(index -> boardSquare.getIncreased(fileCompare * -index, 0))
                .collect(Collectors.toSet());
            if (boardSquaresForCastling.size() == getNonContains(board, boardSquaresForCastling)) {
                totalCheatSheet.add(castlingCheatSheet);
            }
        }
        return totalCheatSheet;
    }

    private int getNonContains(Map<BoardSquare, Piece> board,
        Set<BoardSquare> boardSquaresForCastling) {
        return (int) boardSquaresForCastling.stream()
            .filter(square -> !board.containsKey(square))
            .count() + 1;
    }
}
