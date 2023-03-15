package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Move;
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
}
