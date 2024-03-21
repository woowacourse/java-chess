package domain.piece;

import domain.Camp;
import domain.Rank;
import domain.Square;
import domain.SquareVector;
import java.util.List;
import java.util.Objects;

public class Pawn extends Piece {

    private static final List<SquareVector> BLACK_ATTACK_SQUARE_VECTORS = List.of(new SquareVector(1, -1),
            new SquareVector(-1, -1));
    private static final List<SquareVector> WHITE_ATTACK_SQUARE_VECTORS = List.of(new SquareVector(-1, 1),
            new SquareVector(1, 1));
    private static final SquareVector BLACK_START_SQUARE_VECTOR = new SquareVector(0, -2);
    private static final SquareVector WHITE_START_SQUARE_VECTOR = new SquareVector(0, 2);
    private static final SquareVector BLACK_MOVE_SQUARE_VECTOR = new SquareVector(0, -1);
    private static final SquareVector WHITE_MOVE_SQUARE_VECTOR = new SquareVector(0, 1);

    public Pawn(final Camp color) {
        super(color);
    }

    @Override
    public boolean canMove(final Square source, final Square target) {
        final int x = target.subtractFile(source);
        final int y = target.subtractRank(source);
        final SquareVector squareVector = new SquareVector(x, y);

        Rank rank = Rank.SEVEN;
        SquareVector startSquareVector = BLACK_START_SQUARE_VECTOR;
        SquareVector moveSquareVector = BLACK_MOVE_SQUARE_VECTOR;

        if (camp == Camp.WHITE) {
            rank = Rank.TWO;
            startSquareVector = WHITE_START_SQUARE_VECTOR;
            moveSquareVector = WHITE_MOVE_SQUARE_VECTOR;
        }

        return (source.isRank(rank) && startSquareVector.equals(squareVector))
                || squareVector.equals(moveSquareVector);
    }

    @Override
    public boolean canAttack(final Square source, final Square target) {
        final int x = target.subtractFile(source);
        final int y = target.subtractRank(source);
        final SquareVector squareVector = new SquareVector(x, y);

        List<SquareVector> attackSquareVectors = BLACK_ATTACK_SQUARE_VECTORS;

        if (camp == Camp.WHITE) {
            attackSquareVectors = WHITE_ATTACK_SQUARE_VECTORS;
        }

        return attackSquareVectors.stream().anyMatch(attackVector -> attackVector.equals(squareVector));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof final Pawn piece)) {
            return false;
        }
        return this.camp == piece.camp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(camp, Pawn.class);
    }
}
