package chess.domain.piece;

import chess.domain.Camp;
import chess.domain.Move;
import chess.domain.Square;
import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    private static final List<Move> possibleMoves = makePossibleMove();

    public King(final Camp camp) {
        super(camp);
    }

    private static List<Move> makePossibleMove() {
        return new ArrayList<>(List.of(Move.values()));
    }

    @Override
    public boolean isMovable(final Square source, final Square target, final Move move) {
        if (possibleMoves.contains(move)) {
            return source.isAble(target, move.getFile(), move.getRank());
        }
        return false;
    }
}
