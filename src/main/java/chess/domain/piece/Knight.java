package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.position.move.InvalidMove;
import chess.domain.position.move.PassingMove;
import chess.domain.position.move.PieceMove;

public final class Knight extends Piece {

    public Knight(Camp camp) {
        super(camp, PieceSymbol.KNIGHT);
    }

    @Override
    public PieceMove getMovement(Position from, Position to) {

        if (isPeaceRule(from, to)) {
            return new PassingMove();
        }

        return new InvalidMove();
    }

    @Override
    boolean isPeaceRule(Position from, Position to) {
        int fileGap = to.calculateFileGap(from);
        int rankGap = to.calculateRankGap(from);

        return (Math.abs(rankGap) == 1 && Math.abs(fileGap) == 2)
                || (Math.abs(rankGap) == 2 && Math.abs(fileGap) == 1);
    }
}
