package chess.domain.piece;

import chess.domain.PieceColor;
import chess.domain.PieceType;
import chess.domain.Position;

public class Knight extends Piece {

    public Knight(PieceColor color) {
        super(PieceType.KNIGHT, color);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        return (differenceFile(source, target) == 1 && differenceRank(source, target) == 2) ||
                differenceFile(source, target) == 2 && differenceRank(source, target) == 1;
    }

    private int differenceFile(Position source, Position target) {
        return Math.abs(source.file().compareTo(target.file()));
    }

    private int differenceRank(Position source, Position target) {
        return Math.abs(source.rank().compareTo(target.rank()));
    }
}
