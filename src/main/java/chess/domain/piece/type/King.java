package chess.domain.piece.type;

import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.PieceType;

public class King extends NonPawnPiece {

    public King(Color color) {
        super(PieceType.KING, color);
    }

    @Override
    protected boolean isMovableMove(final Position start, final Position end) {
        int absx = Math.abs(start.findGapOfColumn(end));
        int absy = Math.abs(start.findGapOfRank(end));
        if(absx == 0 && absy == 0) {
            return false;
        }
        return (absx == 0 || absx == 1) && (absy ==0 || absy ==1);
    }

}
