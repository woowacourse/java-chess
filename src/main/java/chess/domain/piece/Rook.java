package chess.domain.piece;

import chess.domain.board.Move;
import chess.domain.board.Square;
import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    private static final List<Move> possibleMoves = makePossibleMove();

    public Rook(final Camp camp) {
        super(camp, Role.ROOK);
    }

    private static List<Move> makePossibleMove() {
        return new ArrayList<>(List.of(Move.values()).subList(0, 4));
    }

    @Override
    public boolean isMovable(final Square source, final Square target, final Move move) {
        return possibleMoves.contains(move);
    }
}
