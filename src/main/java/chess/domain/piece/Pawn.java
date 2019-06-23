package chess.domain.piece;

import chess.domain.Range;
import chess.domain.board.BoardInitializer;
import chess.domain.board.Tile;

public class Pawn extends Piece{
    public Pawn(PieceColor color) {
        super(color);
    }

    @Override
    protected Range getRange(boolean isTargetEmpty, Tile current) {
        if(getColor() == PieceColor.BLACK){
            if(isTargetEmpty){
                return Range.BLACK_PAWN_ATTACK_RANGE;
            }
            if(current.isEqualRow(BoardInitializer.BLACK_PAWNS_ROW)){
                return Range.BLACK_START_PAWN_RANGE;
            }
            return Range.BLACK_PAWN_RANGE;
        }

        if(isTargetEmpty){
            return Range.WHITE_PAWN_ATTACK_RANGE;
        }
        if(current.isEqualRow(BoardInitializer.WHITE_PAWNS_ROW)){
            return Range.WHITE_START_PAWN_RANGE;
        }
        return Range.WHITE_PAWN_RANGE;
    }
}
