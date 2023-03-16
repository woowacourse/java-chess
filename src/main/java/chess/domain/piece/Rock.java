package chess.domain.piece;

import chess.domain.Camp;
import chess.domain.Move;
import chess.domain.Role;
import chess.domain.Square;
import java.util.ArrayList;
import java.util.List;

public class Rock extends Piece {
    private static final List<Move> possibleMoves = makePossibleMove();

    public Rock(final Camp camp) {
        super(camp, Role.ROCK);
    }

    private static List<Move> makePossibleMove() {
        return new ArrayList<>(List.of(Move.values()).subList(0, 4));
    }

    @Override
    public boolean isMovable(final Square source, final Square target, final Move move) {
        return possibleMoves.contains(move);
    }
}
