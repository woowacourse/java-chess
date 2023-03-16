package chess.domain.piece;

import chess.domain.position.Position;

public final class Bishop extends Piece {

    public Bishop(Camp camp) {
        super(camp, PieceSymbol.BISHOP);
    }

    public boolean isMovable(Position from, Position to) {
        int rankGap = from.calculateFileGap(to);
        int fileGap = from.calculateRankGap(to);

        return Math.abs(rankGap) == Math.abs(fileGap);
    }
}
