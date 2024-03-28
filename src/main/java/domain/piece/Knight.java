package domain.piece;

import domain.ChessVector;
import domain.Team;
import domain.square.Square;

import java.util.List;

public class Knight extends Piece {

    private static final List<ChessVector> MOVABLE_VECTORS = List.of(
            new ChessVector(1, 2), new ChessVector(1, -2), new ChessVector(-1, 2), new ChessVector(-1, -2),
            new ChessVector(2, 1), new ChessVector(2, -1), new ChessVector(-2, 1), new ChessVector(-2, -1));

    public Knight(final Team team) {
        super(team, PieceType.KNIGHT);
    }

    @Override
    public boolean canMove(final Square source, final Square target) {
        final ChessVector chessVector = target.calculateVector(source);

        return MOVABLE_VECTORS.contains(chessVector);
    }

    @Override
    public boolean canAttack(final Square source, final Square target) {
        return canMove(source, target);
    }
}
