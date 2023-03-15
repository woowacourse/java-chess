package chess.domain.piece;

import chess.domain.Camp;
import chess.domain.Move;
import chess.domain.Square;
import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {
    private static final List<Move> possibleMoves = makePossibleMove();

    public Queen(final Camp camp) {
        super(camp);
    }

    private static List<Move> makePossibleMove() {
        return new ArrayList<>(List.of(Move.values()));
    }

    @Override
    public boolean isMovable(final Square source, final Square target, final Move move) {
        return possibleMoves.contains(move);
    }
}
