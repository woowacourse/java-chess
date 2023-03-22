package chess.domain.piece;

import static chess.domain.move.Direction.DOWN;
import static chess.domain.move.Direction.LEFT;
import static chess.domain.move.Direction.RIGHT;
import static chess.domain.move.Direction.UP;

import java.util.Set;

import chess.domain.move.Move;

public class Pawn extends Piece {

    private static final Set<Move> WHITE_UNTOUCHED_MOVES = Set.of(new Move(UP), new Move(UP, UP));
    private static final Set<Move> BLACK_UNTOUCHED_MOVES = Set.of(new Move(DOWN), new Move(DOWN, DOWN));
    private static final Set<Move> WHITE_TOUCHED_MOVES = Set.of(new Move(UP));
    private static final Set<Move> BLACK_TOUCHED_MOVES = Set.of(new Move(DOWN));
    private static final Set<Move> WHITE_ATTACK_MOVES = Set.of(new Move(UP, LEFT), new Move(UP, RIGHT));
    private static final Set<Move> BLACK_ATTACK_MOVES = Set.of(new Move(DOWN, RIGHT), new Move(DOWN, LEFT));
    private static final int UNTOUCHED_MOVE_SIZE = 2;

    public Pawn(boolean isWhite) {
        super(isWhite, setUpUntouchedMoves(isWhite));
    }

    private Pawn(boolean isWhite, Set<Move> moves) {
        super(isWhite, moves);
    }

    private static Set<Move> setUpUntouchedMoves(boolean isWhite) {
        if (isWhite) {
            return WHITE_UNTOUCHED_MOVES;
        }
        return BLACK_UNTOUCHED_MOVES;
    }

    private static Pawn createTouched(boolean isWhite) {
        if (isWhite) {
            return new Pawn(isWhite, WHITE_TOUCHED_MOVES);
        }
        return new Pawn(isWhite, BLACK_TOUCHED_MOVES);
    }

    @Override
    public Piece touch() {
        if (isUntouched()) {
            return createTouched(isWhite);
        }
        return this;
    }

    private boolean isUntouched() {
        return moves.size() == UNTOUCHED_MOVE_SIZE;
    }

    @Override
    public boolean hasAttackMove(Move move) {
        return getAttackMoves().stream()
                .anyMatch(it -> compareMove(it, move));
    }

    private Set<Move> getAttackMoves() {
        if (isWhite) {
            return WHITE_ATTACK_MOVES;
        }
        return BLACK_ATTACK_MOVES;
    }

    @Override
    public PieceType getType() {
        return PieceType.PAWN;
    }
}
