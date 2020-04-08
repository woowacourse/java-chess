package chess.model.domain.piece;

import chess.model.domain.board.BoardSquare;
import chess.model.domain.board.CastlingSetting;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import util.NullChecker;

public abstract class Piece {

    private final Color color;
    private final Type type;

    protected Piece(Color color, Type type) {
        NullChecker.validateNotNull(color, type);
        this.color = color;
        this.type = type;
    }

    protected Set<BoardSquare> getAllCheatSheet(BoardSquare boardSquare) {
        Set<BoardSquare> availableBoardSquares = new HashSet<>();
        int repeatCount = getRepeatCount();
        for (int count = 1; count <= repeatCount; count++) {
            addCheatSheet(boardSquare, availableBoardSquares, count);
        }
        return availableBoardSquares;
    }

    protected abstract int getRepeatCount();

    private void addCheatSheet(BoardSquare boardSquare, Set<BoardSquare> availableBoardSquares,
        int count) {
        for (Direction direction : color.getChangeDirection(type.getDirections())) {
            int fileIncrementBy = direction.getMultiplyFileAddAmount(count);
            int rankIncrementBy = direction.getMultiplyRankAddAmount(count);
            if (boardSquare.hasIncreased(fileIncrementBy, rankIncrementBy)) {
                availableBoardSquares
                    .add(boardSquare.getIncreased(fileIncrementBy, rankIncrementBy));
            }
        }
    }

    public abstract Set<BoardSquare> getCheatSheet(BoardSquare boardSquare,
        Map<BoardSquare, Piece> board,
        Set<CastlingSetting> castlingElements);

    public Set<BoardSquare> getCheatSheet(BoardSquare boardSquare,
        Map<BoardSquare, Piece> board) {
        return getCheatSheet(boardSquare, board, new HashSet<>());
    }

    protected Set<BoardSquare> findSquaresToRemove(BoardSquare boardSquare, int fileAddAmount,
        int rankAddAmount) {
        Set<BoardSquare> squaresToRemove = new HashSet<>();
        for (int i = 0, file = 0, rank = 0; i < BoardSquare.MAX_FILE_AND_RANK_COUNT;
            i++, file += fileAddAmount, rank += rankAddAmount) {
            if (boardSquare.hasIncreased(file, rank)) {
                squaresToRemove.add(boardSquare.getIncreased(file, rank));
            }
        }
        squaresToRemove.remove(boardSquare);
        return squaresToRemove;
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public double getScore() {
        return type.getScore();
    }

    public boolean isSameColor(Piece piece) {
        return this.color == piece.color;
    }

    public boolean isSameType(Type type) {
        return this.type == type;
    }
}