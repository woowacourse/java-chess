package chess.domain.piece;

import chess.domain.board.BoardSquare;
import util.NullChecker;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class Piece implements Movable {

    private final Color color;
    private final Type type;

    protected Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
    }

    public String getLetter() {
        return color.getApplyTypeName(type);
    }

    public Set<BoardSquare> getAllCheatSheet(BoardSquare boardSquare) {
        NullChecker.validateNotNull(boardSquare);
        Set<BoardSquare> availableBoardSquares = new HashSet<>();
        int repeatCount = type.getRepeatCount(BoardSquare.MAX_FILE_AND_RANK_COUNT, BoardSquare.MIN_FILE_AND_RANK_COUNT);
        for (int i = 1; i <= repeatCount; i++) {
            for (Direction direction : color.getChangeDirection(type.getDirections())) {
                availableBoardSquares.add(boardSquare.addIfInBoundary(direction.getMultiplyFileAddAmount(i), direction.getMultiplyRankAddAmount(i)));
            }
        }
        availableBoardSquares.remove(boardSquare);
        return availableBoardSquares;
    }

    @Override
    public abstract Set<BoardSquare> getCheatSheet(BoardSquare boardSquare, Map<BoardSquare, Piece> board);

    protected Set<BoardSquare> findSquaresToRemove(BoardSquare s, int fileAddAmount, int rankAddAmount) {
        Set<BoardSquare> squaresToRemove = new HashSet<>();
        int file = 0;
        int rank = 0;
        for (int i = 0; i < 8; i++, file += fileAddAmount, rank += rankAddAmount) {
            squaresToRemove.add(s.addIfInBoundary(file, rank));
        }
        squaresToRemove.remove(s);
        return squaresToRemove;
    }

    public boolean isBlack() {
        return color == Color.BLACK;
    }

    public double getScore() {
        return type.getScore();
    }

    public boolean isSameColor(Piece piece) {
        return this.color == piece.color;
    }

}