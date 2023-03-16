package chess.domain.piece;

import chess.domain.Camp;
import chess.domain.Role;
import chess.domain.Square;
import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    private static final List<KnightMove> possibleMoves = makePossibleMove();

    public Knight(final Camp camp) {
        super(camp, Role.KNIGHT);
    }

    private static List<KnightMove> makePossibleMove() {
        return new ArrayList<>(List.of(KnightMove.values()));
    }

    @Override
    public boolean isMovable(final Square source, final Square target, final KnightMove knightMove) {
        return possibleMoves.contains(knightMove);
    }
}
