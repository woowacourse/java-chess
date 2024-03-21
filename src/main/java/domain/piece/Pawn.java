package domain.piece;

import domain.Camp;
import domain.Rank;
import domain.Square;
import domain.SquareVector;
import java.util.List;

public class Pawn extends Piece {

    private static final List<SquareVector> BLACK_ATTACK_SQUARE_VECTORS = List.of(new SquareVector(1, -1),
            new SquareVector(-1, -1));
    private static final List<SquareVector> WHITE_ATTACK_SQUARE_VECTORS = List.of(new SquareVector(-1, 1),
            new SquareVector(1, 1));
    private static final SquareVector BLACK_START_SQUARE_VECTOR = new SquareVector(0, -2);
    private static final SquareVector WHITE_START_SQUARE_VECTOR = new SquareVector(0, 2);
    private static final SquareVector BLACK_MOVE_SQUARE_VECTOR = new SquareVector(0, -1);
    private static final SquareVector WHITE_MOVE_SQUARE_VECTOR = new SquareVector(0, 1);

    private static final PieceType PIECE_TYPE = PieceType.PAWN;

    public Pawn(final Camp color) {
        super(color);
    }

    @Override
    public boolean canMove(final Square source, final Square target) {
        final SquareVector squareVector = SquareVector.of(source, target);

        Rank rank = Rank.SEVEN;
        SquareVector startSquareVector = BLACK_START_SQUARE_VECTOR;
        SquareVector moveSquareVector = BLACK_MOVE_SQUARE_VECTOR;

        if (camp == Camp.WHITE) {
            rank = Rank.TWO;
            startSquareVector = WHITE_START_SQUARE_VECTOR;
            moveSquareVector = WHITE_MOVE_SQUARE_VECTOR;
        }

        return (source.getRank() == rank && startSquareVector.equals(squareVector))
                || squareVector.equals(moveSquareVector);
    }

    @Override
    public boolean canAttack(final Square source, final Square target) {
        final SquareVector squareVector = SquareVector.of(source, target);

        List<SquareVector> attackSquareVectors = BLACK_ATTACK_SQUARE_VECTORS;

        if (camp == Camp.WHITE) {
            attackSquareVectors = WHITE_ATTACK_SQUARE_VECTORS;
        }

        return attackSquareVectors.stream().anyMatch(attackVector -> attackVector.equals(squareVector));
    }

    @Override
    public PieceType getPieceType() {
        return PIECE_TYPE;
    }
}
