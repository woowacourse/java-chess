package chess.domain.piece.type;

import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.PieceType;

public class Queen extends SlidingPiece {

    public Queen(Color color) {
        super(PieceType.QUEEN, color);
    }

    @Override
    protected boolean isMovableMove(final Position start, final Position end) {
        int absx = Math.abs(start.findGapOfColumn(end));
        int absy = Math.abs(start.findGapOfRank(end));
        if(absx == 0 && absy == 0) {
            return false;
        }
        return (absx == 0 || absy ==0) || absx == absy;
    }

    //다음 리팩토링에서 적용할 것
//    public boolean isNotStay(final int gapOfColumn, int gapOfRank) {
//        return !(gapOfColumn == 0 && gapOfRank ==0);
//    }
//
//    public boolean isStraightMove(final int gapOfColumn, int gapOfRank) {
//        return isNotStay(gapOfColumn, gapOfRank) && (gapOfColumn == 0 || gapOfRank ==0);
//    }
//    public boolean isDiagonalMove(final int gapOfColumn, int gapOfRank) {
//        return isNotStay(gapOfColumn, gapOfRank) && gapOfColumn == gapOfRank;
//    }

}
