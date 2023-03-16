package chess.domain.piece;

import chess.domain.Camp;
import chess.domain.Move;
import chess.domain.Role;
import chess.domain.Square;
import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    private static final List<Move> possibleMoves = makePossibleMove();

    public Bishop(final Camp camp) {
        super(camp, Role.BISHOP);
    }

    private static List<Move> makePossibleMove() {
        return new ArrayList<>(List.of(Move.values()).subList(4, 8));
    }

    @Override
    public boolean isMovable(final Square source, final Square target, final Move move) {
        return possibleMoves.contains(move);
    }
}
