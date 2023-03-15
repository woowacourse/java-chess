package chess.domain.piece;

import chess.domain.move.Direction;
import chess.domain.move.Move;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Pawn extends Piece {

    private static final Move WHITE_UNIT_MOVE = new Move(List.of(Direction.UP));
    private static final int UNTOUCHED_MOVE_SIZE = 2;

    private Pawn(boolean isWhite, Set<Move> moves) {
        super(isWhite, moves);
    }

    public static Pawn from(boolean isWhite) {
        Set<Move> whiteMoves = Set.of(WHITE_UNIT_MOVE, WHITE_UNIT_MOVE.repeat(2));
        if (isWhite) {
            return new Pawn(true, whiteMoves);
        }
        Set<Move> blackMoves = convertColor(whiteMoves);
        return new Pawn(false, blackMoves);
    }

    private static Set<Move> convertColor(Set<Move> moves) {
        return moves.stream()
                .map(Move::flipHorizontal)
                .collect(Collectors.toSet());
    }

    @Override
    public Piece touch() {
        if (isUntouched()) {
            return createTouchedPawn();
        }
        return this;
    }

    private Pawn createTouchedPawn() {
        Set<Move> whiteMoves = Set.of(WHITE_UNIT_MOVE);
        if (isWhite) {
            return new Pawn(true, whiteMoves);
        }
        return new Pawn(false, convertColor(whiteMoves));
    }

    private boolean isUntouched() {
        return moves.size() == UNTOUCHED_MOVE_SIZE;
    }

    @Override
    public boolean hasAttackMove(Move attackMove) {
        boolean hasAttackMove = false;
        for (Move pieceMove : getAttackMoves()) {
            hasAttackMove = hasAttackMove || compareMove(pieceMove, attackMove);
        }
        return hasAttackMove;
    }

    private Set<Move> getAttackMoves() {
        Set<Move> whiteAttackMoves = Set.of(
                new Move(List.of(Direction.UP, Direction.RIGHT)),
                new Move(List.of(Direction.UP, Direction.LEFT)));
        if (isWhite) {
            return whiteAttackMoves;
        }
        return convertColor(whiteAttackMoves);
    }
}
