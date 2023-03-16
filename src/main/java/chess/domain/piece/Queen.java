package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.position.move.BlockingMove;
import chess.domain.position.move.InvalidMove;
import chess.domain.position.move.PieceMove;

public final class Queen extends Piece {

    public Queen(Camp camp) {
        super(camp, PieceSymbol.QUEEN);
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
        int rankGap = from.calculateFileGap(to);
        int fileGap = from.calculateRankGap(to);

        return (Math.abs(rankGap) == Math.abs(fileGap))
                || Math.abs(rankGap) == 0 || Math.abs(fileGap) == 0;
    }
}
