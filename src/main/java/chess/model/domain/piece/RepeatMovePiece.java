package chess.model.domain.piece;

import chess.model.domain.board.BoardSquare;
import chess.model.domain.board.CastlingSetting;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class RepeatMovePiece extends Piece {

    protected RepeatMovePiece(Color color, Type type) {
        super(color, type);
    }

    @Override
    protected int getRepeatCount() {
        return BoardSquare.MAX_FILE_AND_RANK_COUNT - BoardSquare.MIN_FILE_AND_RANK_COUNT;
    }

    @Override
    public Set<BoardSquare> getCheatSheet(BoardSquare boardSquare, Map<BoardSquare, Piece> board,
        Set<CastlingSetting> castlingElements) {
        Set<BoardSquare> allCheatSheet = getAllCheatSheet(boardSquare);
        Set<BoardSquare> containSquares = getContainsSquares(board, allCheatSheet);
        for (BoardSquare cheatSheet : containSquares) {
            int fileCompare = cheatSheet.getFileCompare(boardSquare);
            int rankCompare = cheatSheet.getRankCompare(boardSquare);
            allCheatSheet.removeAll(findSquaresToRemove(cheatSheet, fileCompare, rankCompare));
            allCheatSheet.removeAll(getSameColorSquare(board, cheatSheet));
        }
        return allCheatSheet;
    }

    private Set<BoardSquare> getContainsSquares(Map<BoardSquare, Piece> board,
        Set<BoardSquare> allCheatSheet) {
        return allCheatSheet.stream()
            .filter(board::containsKey)
            .collect(Collectors.toSet());
    }

    private Set<BoardSquare> getSameColorSquare(Map<BoardSquare, Piece> board,
        BoardSquare containSquare) {
        Set<BoardSquare> sameColorSquare = new HashSet<>();
        if (isSameColor(board.get(containSquare))) {
            sameColorSquare.add(containSquare);
        }
        return sameColorSquare;
    }
}
