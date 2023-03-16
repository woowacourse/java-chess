package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.position.move.BlockingMove;
import chess.domain.position.move.InvalidMove;
import chess.domain.position.move.PieceMove;

public final class Rook extends Piece {

    public Rook(Camp camp) {
        super(camp, PieceSymbol.ROOK);
    }

    @Override
    public PieceMove getMovement(Position from, Position to) {
        if (isPeaceRule(from, to)) {
            return new BlockingMove();
        }

        return new InvalidMove();
    }

    @Override
    boolean isPeaceRule(Position from, Position to) {
        int fileGap = to.calculateFileGap(from);
        int rankGap = to.calculateRankGap(from);

        return Math.abs(rankGap) == 0
                || Math.abs(fileGap) == 0;
    }
}
