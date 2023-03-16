package chess.domain.piece;

import chess.domain.position.Position;

public final class Knight extends Piece {

    public Knight(Camp camp) {
        super(camp, PieceSymbol.KNIGHT);
    }

    @Override
    public boolean isMovable(Position from, Position to) {
        int rankGap = from.calculateFileGap(to);
        int fileGap = from.calculateRankGap(to);

        return (Math.abs(rankGap) == 1 && Math.abs(fileGap) == 2)
                || (Math.abs(rankGap) == 2 && Math.abs(fileGap) == 1);
    }
}
