package chess.domain.piece;

import chess.domain.board.MoveType;
import chess.domain.move.Direction;
import chess.domain.move.Move;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Pawn extends Piece {

    private static final Move WHITE_UNIT_MOVE = new Move(List.of(Direction.UP));
    private static final int UNTOUCHED_MOVE_SIZE = 2;

    public Pawn(Color color) {
        super(color, setUpMoves(color));
    }

    private Pawn(Color color, Set<Move> moves) {
        super(color, moves);
    }

    private static Set<Move> setUpMoves(Color color) {
        Set<Move> whiteMoves = Set.of(WHITE_UNIT_MOVE, WHITE_UNIT_MOVE.repeat(2));
        if (color == Color.WHITE) {
            return whiteMoves;
        }
        return convertColor(whiteMoves);
    }

    private static Set<Move> convertColor(Set<Move> moves) {
        return moves.stream()
                .map(Move::flipVertical)
                .collect(Collectors.toSet());
    }

    @Override
    public Piece touch() {
        if (isUntouched()) {
            return createTouchedPawn();
        }
        return this;
    }

    private boolean isUntouched() {
        return moves.size() == UNTOUCHED_MOVE_SIZE;
    }

    private Pawn createTouchedPawn() {
        Set<Move> whiteMoves = Set.of(WHITE_UNIT_MOVE);
        if (color == Color.WHITE) {
            return new Pawn(Color.WHITE, whiteMoves);
        }
        return new Pawn(Color.BLACK, convertColor(whiteMoves));
    }

    @Override
    public boolean isValidMove(Move move, MoveType moveType) {
        Set<Move> validMoves = moves;
        if (moveType == MoveType.ATTACK) {
            validMoves = getAttackMoves();
        }
        return super.checkContainment(validMoves, move);
    }

    private Set<Move> getAttackMoves() {
        Set<Move> whiteAttackMoves = Set.of(
                new Move(Direction.UP, Direction.RIGHT),
                new Move(Direction.UP, Direction.LEFT));
        if (color == Color.WHITE) {
            return whiteAttackMoves;
        }
        return convertColor(whiteAttackMoves);
    }

    @Override
    public PieceType getType() {
        return PieceType.PAWN;
    }
}
