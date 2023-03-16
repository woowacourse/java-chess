package chess.domain.piece;

import chess.domain.position.Position;

public final class King extends Piece {

    public King(Camp camp) {
        super(camp, PieceSymbol.KING);
    }

    @Override
    public boolean isMovable(Position from, Position to) {
        int fileGap = from.calculateFileGap(to);
        int rankGap = from.calculateRankGap(to);

        return (Math.abs(rankGap) == 0 && Math.abs(fileGap) == 1)
                || (Math.abs(rankGap) == 1 && Math.abs(fileGap) == 0)
                || (Math.abs(rankGap) == 1 && Math.abs(fileGap) == 1);
    }
}
