package chess.domain.piece.type;

import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.PieceType;

public class Knight extends NonPawnPiece {

    public Knight(Color color) {
        super(PieceType.KNIGHT, color);
    }

    @Override
    protected boolean isMovableMove(final Position start, final Position end) {
        int absx = Math.abs(start.findGapOfColumn(end));
        int absy = Math.abs(start.findGapOfRank(end));

        return (absx == 1 && absy ==2) || (absx ==2 && absy ==1);
    }

}
