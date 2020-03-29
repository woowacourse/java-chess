package chess.domain.piece;

import chess.domain.board.BoardSquare;
import chess.domain.board.ChessInitialSetting;
import chess.domain.movement.Direction;
import chess.domain.movement.Movable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import util.NullChecker;

public abstract class Piece implements Movable {

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
            availableBoardSquares
                .add(boardSquare.getAddIfInBoundaryOrMyself(fileIncrementBy, rankIncrementBy));
        }
        availableBoardSquares.remove(boardSquare);
    }

    @Override
    public abstract Set<BoardSquare> getCheatSheet(BoardSquare boardSquare,
        Map<BoardSquare, Piece> board,
        Set<ChessInitialSetting> castlingElements);

    public Set<BoardSquare> getCheatSheet(BoardSquare boardSquare,
        Map<BoardSquare, Piece> board) {
        return getCheatSheet(boardSquare, board, new HashSet<>());
    }

    protected Set<BoardSquare> findSquaresToRemove(BoardSquare s, int fileAddAmount,
        int rankAddAmount) {
        Set<BoardSquare> squaresToRemove = new HashSet<>();
        for (int i = 0, file = 0, rank = 0; i < 8;
            i++, file += fileAddAmount, rank += rankAddAmount) {
            squaresToRemove.add(s.getAddIfInBoundaryOrMyself(file, rank));
        }
        squaresToRemove.remove(s);
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

    public String getLetter() {
        return type.getLetter();
    }

    public boolean isSameType(Type type) {
        return this.type == type;
    }
}