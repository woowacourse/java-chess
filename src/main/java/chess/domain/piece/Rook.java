package chess.domain.piece;

import chess.domain.position.Position;

public final class Rook extends Piece {

    public Rook(Camp camp) {
        super(camp, PieceSymbol.ROOK);
    }

    @Override
    public boolean isMovable(Position from, Position to) {
        int rankGap = from.calculateFileGap(to);
        int fileGap = from.calculateRankGap(to);

        return Math.abs(rankGap) == 0
                || Math.abs(fileGap) == 0;
    }
}
