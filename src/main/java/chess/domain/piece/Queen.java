package chess.domain.piece;

import chess.domain.position.Position;

public final class Queen extends Piece {

    public Queen(Camp camp) {
        super(camp, PieceSymbol.QUEEN);
    }

    @Override
    public boolean isMovable(Position from, Position to) {
        int rankGap = from.calculateFileGap(to);
        int fileGap = from.calculateRankGap(to);

        return (Math.abs(rankGap) == Math.abs(fileGap))
                || Math.abs(rankGap) == 0 || Math.abs(fileGap) == 0;
    }
}
