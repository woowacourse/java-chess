package chess.domain.piece;

import chess.domain.board.BoardSquare;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class RepeatPiece extends Piece {

    protected RepeatPiece(Color color, Type type) {
        super(color, type);
    }

    @Override
    public Set<BoardSquare> getCheatSheet(BoardSquare boardSquare, Map<BoardSquare, Piece> board) {
        Set<BoardSquare> boardSquares = getAllCheatSheet(boardSquare);
        Set<BoardSquare> containSquares = boardSquares.stream()
                .filter(board::containsKey)
                .collect(Collectors.toSet());
        for (BoardSquare containSquare : containSquares) {
            int fileCompare = containSquare.getFileCompare(boardSquare);
            int rankCompare = containSquare.getRankCompare(boardSquare);
            Set<BoardSquare> squaresToRemove = findSquaresToRemove(containSquare, fileCompare, rankCompare);
            addSameColorSquare(board, containSquare, squaresToRemove);
            boardSquares.removeAll(squaresToRemove);
        }
        return boardSquares;
    }

    private void addSameColorSquare(Map<BoardSquare, Piece> board, BoardSquare containSquare, Set<BoardSquare> squaresToRemove) {
        if (isSameColor(board.get(containSquare))) {
            squaresToRemove.add(containSquare);
        }
    }
}
